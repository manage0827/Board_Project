select * from tbl_board;

delete from TBL_BOARD;

create sequence tbl_board_seq;

insert into tbl_board(bno,title,content,writer) values(tbl_board_seq.NEXTVAL,'�ȳ�','���߳�','��');

select bno, title, writer, regdate, viewcnt  from tbl_board;

select count(*) from tbl_board