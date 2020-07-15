CREATE TABLE IF NOT EXISTS t_user_token(
	id INT PRIMARY KEY auto_increment,
	userid INT NOT NULL UNIQUE COMMENT '用户id',
	token CHAR(49) COMMENT '由uuid+时间戳组成',
	createtime TIMESTAMP NOT NULL COMMENT 'token生成时间',
	FOREIGN KEY (userid) REFERENCES t_user(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;