--게시판관련
INSERT INTO article(title, content) VALUES('가가가가', '1111');
INSERT INTO article(title, content) VALUES('나나나나', '2222');
INSERT INTO article(title, content) VALUES('다다다다', '3333');

INSERT INTO article(title, content) VALUES('당신의 인생 영화는?','댓글 고');
INSERT INTO article(title, content) VALUES('당신의 소울 푸드는?','댓글 고고');
INSERT INTO article(title, content) VALUES('당신의 취미는?','댓글 ㄱㄱㄱ');

INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Park','굿윌헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Kim','아이 엠 샘');
INSERT INTO comment(article_id, nickname, body) VALUES(4, 'Jeong','아가씨');

INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Park','치킨');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Kim','샤브샤브');
INSERT INTO comment(article_id, nickname, body) VALUES(5, 'Jeong','고기');

INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Park','조깅');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Kim','유튜브 시청');
INSERT INTO comment(article_id, nickname, body) VALUES(6, 'Jeong','독서');

--member
INSERT INTO member(id, email, password) VALUES(1, 'aaaa@naver.com', '1111');
INSERT INTO member(id, email, password) VALUES(2, 'bbbb@naver.com', '2222');
INSERT INTO member(id, email, password) VALUES(3, 'cccc@naver.com', '3333');

--coffee
INSERT INTO coffee(name, price) VALUES('아메리카노', '4500');
INSERT INTO coffee(name, price) VALUES('라떼', '5000');
INSERT INTO coffee(name, price) VALUES('카페모카', '5500');