-- �Խ��� ���̺��� ����
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

-- ���̺� �׽�ũ ���� �߰�
insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(1, 0, '�׽�Ʈ���Դϴ�.', '�׽�Ʈ���Դϴ�.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(2, 0, '�ȳ��ϼ���.', '��ǰ �ı��Դϴ�.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(3, 2, '�亯�Դϴ�.', '��ǰ�ı⿡ ���� �亯�Դϴ�.', null, sysdate, 'hong');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(5, 3, '�亯�Դϴ�.', '��ǰ �����ϴ�.', null, sysdate, 'lee');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(4, 0, '�������Դϴ�.', '������ �׽�Ʈ���Դϴ�.', null, sysdate, 'kim');

insert into t_board(articleNO, parentNO, title, content, imageFileName, writedate, id)
values(6, 2, '��ǰ �ı��Դϴ�.', '�̼��ž��� ��ǰ ��� �ı⸦ �ø��ϴ�.', null, sysdate, 'lee');

commit;
select * from t_board;

-- ����Ŭ���� �����ϴ� ������ SQL��
SELECT LEVEL, -- ����Ŭ���� �����ϴ� ���� �÷����� ���� ���̸� ��Ÿ��(�θ���� 1)
    articleNO,
    parentNO,
    LPAD(' ', 4*(level-1)) || title title,
    content,
    writeDate,
    id
FROM t_board
START WITH parentNO=0 -- ������ �������� �ֻ��� ������ �ο츦 �ĺ��ϴ� ������ ���
CONNECT BY PRIOR articleNO = parentNO -- ���������� ������� ����Ǵ����� ����ϴ� �κ�
ORDER SIBLINGS BY articleNO DESC; -- ���������� ��ȸ�� ������ �ٽ� articleNO�� �̿��Ͽ� ������������ ���� �� ���� ���

-- ����Ŭ�� ������ SQL���� �̿��� �θ� �ۿ� ���� �ڽ� ���� �����ϴ� SQL��
DELETE FROM t_board
WHERE articleNO in (
        SELECT articleNO FROM t_board
        START WITH articleNO= 2 -- �۹�ȣ�� 2�� �۰� �ڽı��� ����
        CONNECT BY PRIOR articleNO = parentNO
    );
    
-- section�� pageNum���� �� ��� ��ȸ�ϴ� SQL��
    -- ������������ ����Ŭ���� �����ϴ� �����÷��� ROWNUM�� �̿���.
SELECT *
FROM (
    SELECT ROWNUM as recNum, -- ���������� ��ȸ�� ���ڵ��� ROWNUM(recNum)�� ǥ�õǵ��� ��ȸ
            LVL,
            articleNO,
            parentNO,
            title,            
            id,
            writeDate
    FROM (
        SELECT LEVEL as LVL, -- ������ SQL������ ���� �������� ��ȸ
                articleNO,
                parentNO,
                title,
                id,
                writeDate
        FROM t_board
        START WITH parentNO=0
        CONNECT BY PRIOR articleNO=parentNO
        ORDER SIBLINGS BY articleNO DESC
    )
)
WHERE recNum between(section-1)*100+(pageNum-1)*10+1 and (section-1)*100+pageNum*10; 
    -- section�� pageNum������ ���ǽ��� recNum������ ���� �� ��ȸ�� �� �� �ش��ϴ� ���� �ִ� ��� ���������� ��ȸ

-- board09 : ���۰� �亯�ۿ� ������ ���̱�    
-- 1step. ���� �ð� ���ϱ�
select sysdate from dual;

-- 2step. �� �ۼ� �ð�(24�ð��̳�) ���ϱ�
select decode(round(sysdate - writedate),0,'true','false') as isNewArticle from t_board;

-- board10 : ������ ���
select * from t_board;
update t_board
set notice_yn = 'n';
commit;