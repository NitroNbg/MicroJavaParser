package rs.ac.bg.etf.pp1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class RPNParser {
	public static final int LEFT_BRACKET = 1001;
	public static final int RIGHT_BRACKET = 1002;

	private Queue<Integer> output = new LinkedList<Integer>();
	private Stack<Integer> operatorStack = new Stack<Integer>();
	
	public void newToken(int token, boolean isOperator) {
		if (token == LEFT_BRACKET) {
			operatorStack.push(token);
		}
		else if (token == RIGHT_BRACKET) {
			while(operatorStack.peek() != LEFT_BRACKET) {
				output.add(operatorStack.pop());
			}
			operatorStack.pop();
		}
		else if (isOperator) {
			if (token == Code.add || token == Code.sub) {
				if (operatorStack.isEmpty()) {
					operatorStack.push(token);
				}
				else {
					int top = operatorStack.peek();
					while (top == Code.mul || top == Code.div || top == Code.rem) {
						output.add(operatorStack.pop());
						if (operatorStack.isEmpty()) {
							operatorStack.push(token);
							return;
						}
						top = operatorStack.peek();
					}
					operatorStack.push(token);
				}
			}
			else {
				operatorStack.push(token);
			}
		}
		else {
			output.add(token);
		}
	}
	
	public void loadConst(int token) {
		if (0 <= token && token <= 5) {
    			put(Code.const_n + token);
		}
		else if (token==-1) {
    			put(Code.const_m1);
		}
		else {
    			put(Code.const_);
    			put4(token);
    		}
	}
	
	public void load(Obj o) {
		switch (o.getKind()) {
	    	
		      case Obj.Con:
		        if (o.getType() == Tab.nullType) 
		            put(Code.const_n + 0);
		        else 
		            loadConst(o.getAdr()); 
		        break;
		        
		      case Obj.Var:
		        if (o.getLevel()==0) { // global variable 
		        	  put(Code.getstatic); put2(o.getAdr()); 
		        	  break; 
		        }
		        // local variable
		        if (0 <= o.getAdr() && o.getAdr() <= 3) 
		            put(Code.load_n + o.getAdr());
		        else { 
		        	 put(Code.load); put(o.getAdr()); 
		        } 
		        break;
		        
		      case Obj.Fld:
		        put(Code.getfield); put2(o.getAdr()); 
		        break;

		      case Obj.Elem:
		        if (o.getType().getKind() == Struct.Char) put(Code.baload);
		        else put(Code.aload);
		        break;

		      default:
		        System.err.println("Greška: nelegalan operand u Code.load");
		    }
	}
	
	private void put(int x) {
		newToken(x, false);
	}
	
	private void put2(int x) {
		put(x>>8);
		put(x);
	}
	
	private void put4(int x) {
		put2(x>>16);
		put2(x);
	}

	public void outputTo(RPNParser rpnParser) {
		while(!this.operatorStack.isEmpty()) {
			output.add(operatorStack.pop());
		}
		while(!this.output.isEmpty()) {
			rpnParser.newToken(output.poll(), false);
		}
	}
	
	public int outputTo(byte[] buffer, int startPointer) {
		while(!this.operatorStack.isEmpty()) {
			output.add(operatorStack.pop());
		}
		while(!this.output.isEmpty()) {
			buffer[startPointer++] = this.output.poll().byteValue();
		}
		return startPointer;
	}

	public boolean isEmpty() {
		return this.output.isEmpty() && this.operatorStack.isEmpty();
	}

	public void callDestination(int i) {
		put2(i - output.size() - 2);
	}
}
