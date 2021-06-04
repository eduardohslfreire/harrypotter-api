CREATE TABLE tab_character 
   (id SERIAL,
	name VARCHAR(70) NOT NULL,
	role VARCHAR(20) NOT NULL,
	school VARCHAR(50) NOT NULL,
	house VARCHAR(50) NOT NULL,
	patronus VARCHAR(20) NOT NULL);

ALTER TABLE tab_character ADD CONSTRAINT pk_character PRIMARY KEY (id);
  
CREATE INDEX idx_house_tab_character ON tab_character USING hash (house);
