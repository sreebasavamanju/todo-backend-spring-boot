CREATE TABLE todos (
	username text, 
	id timeuuid,
	title text,
	completed  boolean,
	PRIMARY KEY (username,id) 
);
