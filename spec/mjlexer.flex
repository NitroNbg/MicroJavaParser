package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// uključivanje podataka o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// uključivanje podataka o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}
	
%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 		{ }
"\t" 	{ }
"\r\n" 	{ }
"\b"		{ }
"\f"		{ }

"program"		{ return new_symbol(sym.PROG, yytext()); }
"break"			{ return new_symbol(sym.BREAK, yytext()); }
"class"			{ return new_symbol(sym.CLASS, yytext()); }
"else"			{ return new_symbol(sym.ELSE, yytext()); }
"const"			{ return new_symbol(sym.CONST, yytext()); }
"if"				{ return new_symbol(sym.IF, yytext()); }
"new"			{ return new_symbol(sym.NEW, yytext()); }
"print"			{ return new_symbol(sym.PRINT, yytext()); }
"read"			{ return new_symbol(sym.READ, yytext()); }
"return"			{ return new_symbol(sym.RETURN, yytext()); }
"void"			{ return new_symbol(sym.VOID, yytext()); }
"do"				{ return new_symbol(sym.DO, yytext()); }
"while"			{ return new_symbol(sym.WHILE, yytext()); }
"continue"		{ return new_symbol(sym.CONTINUE, yytext()); }
"extends"		{ return new_symbol(sym.EXTENDS, yytext()); }
"+"				{ return new_symbol(sym.PLUS, yytext()); }
"-"				{ return new_symbol(sym.MINUS, yytext()); }
"*"				{ return new_symbol(sym.MUL, yytext()); }
"/"				{ return new_symbol(sym.DIV, yytext()); }
"%"				{ return new_symbol(sym.MOD, yytext()); }
"=="				{ return new_symbol(sym.EQUALSEQUALS, yytext()); }
"!="				{ return new_symbol(sym.NOTEQUALS, yytext()); }
">"				{ return new_symbol(sym.GREATER, yytext()); }
">="				{ return new_symbol(sym.GREATEREQUALS, yytext()); }
"<"				{ return new_symbol(sym.LESS, yytext()); }
"<="				{ return new_symbol(sym.LESSEQUALS, yytext()); }
"&&"				{ return new_symbol(sym.AND, yytext()); }
"||"				{ return new_symbol(sym.OR, yytext()); }
"="				{ return new_symbol(sym.EQUALS, yytext()); }
"++"				{ return new_symbol(sym.PLUSPLUS, yytext()); }
"--"				{ return new_symbol(sym.MINUSMINUS, yytext()); }
";"				{ return new_symbol(sym.SEMICOLON, yytext()); }
","				{ return new_symbol(sym.COMMA, yytext()); }
"."				{ return new_symbol(sym.DOT, yytext()); }
"("				{ return new_symbol(sym.LBRACKET, yytext()); }
")"				{ return new_symbol(sym.RBRACKET, yytext()); }
"["				{ return new_symbol(sym.LINDBRACKET, yytext()); }
"]"				{ return new_symbol(sym.RINDBRACKET, yytext()); }
"{"				{ return new_symbol(sym.LCURLY, yytext()); }
"}"				{ return new_symbol(sym.RCURLY, yytext()); }


"//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


"true"|"false"					{return new_symbol (sym.BOOL, yytext().equals("true") ? true : false);}
//"\""[^\"]*"\""					{return new_symbol (sym.STR, yytext().substring(1,yytext().length()-1));}
[0-9]+ 							{return new_symbol (sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]*	{return new_symbol (sym.IDENT, yytext()); }
"'"[\040-\176]"'" 				{return new_symbol (sym.CHAR, new Character (yytext().charAt(1)));}


. { System.err.println("Leksička greška (" + yytext() + ") u liniji " + (yyline+1) + " pod rednim brojem " + (yycolumn+1)); }