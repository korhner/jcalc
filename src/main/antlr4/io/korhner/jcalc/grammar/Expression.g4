grammar Expression;
prog: numExpr | boolExpr ;

numExpr: 	numExpr mulPrecedence numExpr		# multiplicationOperation
		|	numExpr addPrecedence numExpr		# additionOperation
		|	numConstant							# numberValue
		|	'(' numExpr ')'						# numberParentheses
		;

boolExpr: 	unaryPrecedence boolExpr			# unaryOperation
		|	numExpr relPrecedence numExpr		# relationalOperation
		|	numExpr eqPrecedence numExpr		# numberEqualityOperation
		|	boolExpr eqPrecedence boolExpr		# boolEqualityOperation
		|	boolExpr andPrecedence boolExpr		# andOperation
		|	boolExpr orPrecedence boolExpr		# orOperation
		|	boolConstant						# boolValue
		|	'(' boolExpr ')'					# boolParentheses
		;

mulPrecedence: op=(MUL | DIV | MODUO) ;
addPrecedence: op=(PLUS | MINUS) ;

unaryPrecedence: NOT ;
relPrecedence: op=(LT | LTEQ | GT | GTEQ) ;
eqPrecedence: op=(EQ | NOTEQ) ;

andPrecedence: AND ;
orPrecedence: OR ;

numConstant: MINUS? (decimal | integer) ;
integer: DIGITS ;
decimal: DIGITS? '.' DIGITS ;

boolConstant: op=(TRUE | FALSE) ;

DIGITS: [0-9]+ ;

TRUE: 'T' ;
FALSE: 'F' ;

MUL: '*' ;
DIV: '/' ;

PLUS: '+' ;
MINUS: '-' ;
MODUO: '%' ; 

NOT : '!' ;

LT : '<' ;
LTEQ : '<=' ;
GT : '>' ;
GTEQ : '>=' ;

EQ: '=' ;
NOTEQ: '!=' ;

AND: '&&' ;
OR: '||' ;

NEWLINE: '\r'? '\n' ;	// return new line to parser
WS: [ \t]+ -> skip ;	// skip whitespace