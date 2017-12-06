grammar mysql_alter;

ALTER : 'ALTER' ;
EQUAL_SIGN : '=' ;
ADD : ['a''A']['d''D']['d''D'] ; //insensative form of literal 'ADD' 
IGNORE : 'IGNORE' ;
TABLE : 'TABLE' ;
COLUMN : 'COLUMN' ;

Col_name : [a-zA-Z_]+[a-zA-Z0-9_]* ;
						
alter_specification 
	: ADD COLUMN? Col_name (First_or_after Col_name)?
	| table_option
	;

table_option
	: 'AUTO_INCREMENT' EQUAL_SIGN? Boolean_value
	| 
	| 'UNION' EQUAL_SIGN? '('Table_name(','Table_name)*')' ;

Boolean_value
	: 'false'
	| 'true'
	| 'FALSE'
	| 'TRUE' ;
	
Table_name
    : (Database_name'.')?Table_name_without_db_name
	| ('`'Database_name'`''.')?'`'Table_name_without_db_name'`' ;
Database_name : [a-zA-Z$_]+[a-zA-Z0-9$_]* ;
Table_name_without_db_name : [a-zA-Z_]+[a-zA-Z0-9_]* ;


Is_online
	: 'ONLINE'
	| 'OFFLINE'
	;

First_or_after
	: 'FIRST'
	| 'AFTER'
	;

	
WS : [ \t\r\n]+ -> skip ;


TEST2 : [a-zA-Z_]+[a-zA-Z0-9_]* ;
TEST1 : [a-zA-Z_]+[a-zA-Z0-9_]* ;

alter_statement : ALTER Is_online? IGNORE? TABLE Table_name 
						alter_specification(','alter_specification)* 
						;
