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