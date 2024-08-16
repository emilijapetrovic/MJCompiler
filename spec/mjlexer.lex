
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{
	private Symbol new_symbol(int type){
		return new Symbol(type, yyline+1, yycolumn);
	}
	private Symbol new_symbol(int type, Object value){
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

" "		{}
"\b"	{}
"\t"	{}
"\r\n"	{}
"\f"	{}



"program"	{return new_symbol(sym.PROGRAM, yytext());}
"break"	    {return new_symbol(sym.BREAK, yytext());}

"else"	    {return new_symbol(sym.ELSE, yytext());}
"const"	    {return new_symbol(sym.CONST, yytext());}
"if"	    {return new_symbol(sym.IF, yytext());}
"new"	    {return new_symbol(sym.NEW, yytext());}
"print"	    {return new_symbol(sym.PRINT, yytext());}
"read"	    {return new_symbol(sym.READ, yytext());}
"return"	{return new_symbol(sym.RETURN, yytext());}
"void"	    {return new_symbol(sym.VOID, yytext());}
/*"extends"   {return new_symbol(sym.EXTENDS, yytext());}*/
"continue"	{return new_symbol(sym.CONTINUE, yytext());}
/*"this"	    {return new_symbol(sym.THIS, yytext());}*/
/*"for"	    {return new_symbol(sym.FOR, yytext());}*/
"static"    {return new_symbol(sym.STATIC, yytext());}
"range" {return new_symbol(sym.RANGE, yytext());}
"final"		{return new_symbol(sym.FINAL, yytext());}
"count"		{return new_symbol(sym.COUNT, yytext());}
"//"				{yybegin(COMMENT);}
<COMMENT> . 		{yybegin(COMMENT);}
<COMMENT> "\r\n"	{yybegin(YYINITIAL);}


"+"		{return new_symbol(sym.PLUS, yytext());}
"-"		{return new_symbol(sym.MINUS, yytext());}
"*"		{return new_symbol(sym.MUL, yytext());}
"/"		{return new_symbol(sym.DIV, yytext());}
"%"		{return new_symbol(sym.MOD, yytext());}
"=="	{return new_symbol(sym.EQUALS, yytext());}
"!="	{return new_symbol(sym.NOTEQUALS, yytext());}
">"		{return new_symbol(sym.GREATER, yytext());}
">="	{return new_symbol(sym.GREATEREQUALS, yytext());}
"<"		{return new_symbol(sym.LESS, yytext());}
"<="	{return new_symbol(sym.LESSEQUALS, yytext());}
"&&"	{return new_symbol(sym.AND, yytext());}
"||"	{return new_symbol(sym.OR, yytext());}
"="		{return new_symbol(sym.ISEQUAL, yytext());}
"++"	{return new_symbol(sym.INC, yytext());}
"--"	{return new_symbol(sym.DEC, yytext());}
";"		{return new_symbol(sym.SEMICOLON, yytext());}
":"		{return new_symbol(sym.COLON, yytext());}
","		{return new_symbol(sym.COMMA, yytext());}
"("		{return new_symbol(sym.LPAREN, yytext());}
")"		{return new_symbol(sym.RPAREN, yytext());}
"["		{return new_symbol(sym.LSBRACKET, yytext());}
"]"		{return new_symbol(sym.RSBRACKET, yytext());}
"{"		{return new_symbol(sym.LBRACE, yytext());}
"}"		{return new_symbol(sym.RBRACE, yytext());}
/*"=>"	{return new_symbol(sym.LASSOC, yytext());}*/
":"		{return new_symbol(sym.COLON, yytext());}
"::"	{return new_symbol(sym.DOUBLECOLON, yytext());}
"+++"	{return new_symbol(sym.DOUBLEINC, yytext());}
"."		{return new_symbol(sym.FULLSTOP, yytext());}
"!"		{return new_symbol(sym.EXCLAMATION, yytext());}
"#"		{return new_symbol(sym.HASH, yytext());}

[0-9]+							{return new_symbol(sym.NUMCONST, Integer.valueOf(yytext()));}
"'"."'"							{return new_symbol(sym.CHARCONST, new Character(yytext().charAt(1)));}
("true"|"false")				{return new_symbol(sym.BOOLCONST, yytext().equals("true")?1:0);}
[a-zA-Z][a-zA-Z0-9_]*		    {return new_symbol(sym.IDENT, yytext());}

. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }