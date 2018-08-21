INSERT INTO users VALUES (11,'11', 'Сорокин', 'Алексей','Константинович', '72-723', 'email@pktbcki.ru','ПТКБ','ППСТЛ','ГИП',current_timestamp - 2 day,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP' );
INSERT INTO users VALUES (1,'1', 'George', 'Franklin','midle name', 'telephone', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (2,'', 'Betty', 'Davis','midle name', 'telephone', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (3,'', 'Eduardo', 'Rodriquez','midle name','6085558763','email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (4,'', 'Harold', 'Davis','midle name', '6085553198', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (5,'', 'Peter', 'McTavish','midle name', '6085552765', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (6,'', 'Jean', 'Coleman','midle name', '6085552654', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (7,'', 'Jeff', 'Black','midle name', '6085555387', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (8,'', 'Maria', 'Escobito','midle name', '6085557683', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (9,'', 'David', 'Schroeder','midle name', '6085559435','email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');
INSERT INTO users VALUES (10,'', 'Carlos', 'Estaban','midle name',  '6085555487', 'email@pktbcki.ru','company','department','title',current_timestamp,'ip','numberASOZ',current_timestamp ,current_timestamp ,'1','2', 'Creator_IP', 'Editor_IP');

INSERT INTO logins VALUES ('aks',1,'123',1,11,false,current_timestamp,current_timestamp,0 ,current_timestamp,current_timestamp,  'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO logins VALUES ('login1',1,'123',2,1,false,current_timestamp,current_timestamp,0 ,current_timestamp,current_timestamp,  'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO logins VALUES ('login2',1,'123',3,1,false,current_timestamp,current_timestamp,0 ,current_timestamp,current_timestamp,  'ctreator', 'editor', 'Creator_IP', 'Editor_IP');


INSERT INTO user_roles VALUES (1,'login1','role_name','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (2,'login1','role_name2','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (3,'login2','role_name2','description',current_timestamp,current_timestamp, 2 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (4,'login2','role_name3','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (5,'login2','role_name4','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (6,'aks','role_name3','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (7,'aks','role_name4','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');
INSERT INTO user_roles VALUES (8,'','role_name4','description',current_timestamp,current_timestamp, 1 , 'ctreator', 'editor', 'Creator_IP', 'Editor_IP');

INSERT INTO systems VALUES (1,'АУДИТ РП',1 );
