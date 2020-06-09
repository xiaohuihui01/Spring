DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User (
  userID INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(100) DEFAULT NULL,
  phone VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (UserID)
  );
INSERT INTO User (userID, username, password, email, phone) VALUES
(1, 'kaiyudai', '12345678', 'kydai@fudan.edu.cn', '13666666666'),
(2, 'louhwz', '12345678', 'louhwz@fudan.edu.cn', '13888888888'),
(3, 'cloud friend', '12345678', 'cloudfriend@fudan.edu.cn', '13686868686');