-- 게시판 테이블을 생성
create table t_Board(
    articleNO NUMBER(10) PRIMARY KEY,
    parentNO number(10) default 0,
    title varchar2(500) not null,
    content varchar2(4000),
    imageFileName varchar2(100),
    writedate date default sysdate not null,
    id varchar2(10),
    CONSTRAINT FK_ID FOREIGN KEY(id) REFERENCES t_member(id)    
);

-- 테이블에 테스크 글을 추가
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(1, 0, '테스트글입니다.', '테스트글입니다.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(2, 0, '안녕하세요.', '상품 후기입니다.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(3, 2, '답변입니다.', '상품후기에 대한 답변입니다.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(5, 3, '답변입니다.', '상품 좋습니다.', null, sysdate, 'lee');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(4, 0, '김유신입니다.', '김유신 테스트글입니다.', null, sysdate, 'kim');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(6, 2, '상품 후기입니다.', '이순신씨의 상품 사용 후기를 올립니다.', null, sysdate, 'lee');

commit;
select * from t_board;

-- 오라클에서 제공하는 계층형 SQL문
SELECT LEVEL, -- 오라클에서 제공하는 가상 컬럼으로 글의 깊이를 나타냄(부모글은 1)
    articleNO,
    parentNO,
    LPAD(' ', 4*(level-1)) || title title,
    content,
    writeDate,
    id
FROM t_board
START WITH parentNO=0 -- 계층형 구조에서 최상위 계층의 로우를 식별하는 조건을 명시
CONNECT BY PRIOR articleNO = parentNO -- 계층구조가 어떤식으로 연결되는지를 기술하는 부분
ORDER SIBLINGS BY articleNO DESC; -- 계층형으로 조회된 정보를 다시 articleNO를 이용하여 내림차순으로 정렬 및 최종 출력

-- 오라클의 계층형 SQL문을 이용해 부모 글에 대한 자식 글을 삭제하는 SQL문
DELETE FROM t_board
WHERE articleNO in (
        SELECT articleNO FROM t_board
        START WITH articleNO= 2 -- 글번호가 2인 글가 자식글을 삭제
        CONNECT BY PRIOR articleNO = parentNO
    );