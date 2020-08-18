create table tbl_member
  	  (userId varchar2(20),
  	  userPw varchar2(20),
  	  userName varchar2(20),
  	  userEmail varchar2(30),
  	  userRegdate date default sysdate,
  	  userUpdatedate date default sysdate) 
  select username As userName from tbl_member
  
 select count(*) from tbl_board b, tbl_member m
 
 select * from tbl_board
select * from TBL_MEMBER
 
select b.bno, b.title,b.content, b.regdate, ,b.viewcnt, m.userName from tbl_board b,tbl_member m where b.writer = m.userId(+)

select  a.bno, a.title,a.content, a.regdate, ,a.viewcnt, b.userName from tbl_board a, tbl_member b where a.writer = b.userId
insert into tbl_board(writer,content) values('admin','내용있음')
insert into tbl_board 
select * from TBL_BOARD a, tbl_member b where a.writer = b.userId
select * from tbl_member

select b.bno, b.title, b.content, b.regdate, b.writer ,b.viewcnt, m.userId, m.userName from tbl_board b left outer join TBL_MEMBER m on b.writer = m.userid
select *  from tbl_board b left outer join TBL_MEMBER m on b.writer = m.userId

select count(*) from tbl_board  b, tbl_member m



select * from TBL_BOARD b, TBL_MEMBER m where b.writer = m.userId
			and
				(userName like '%'||'1'||'%'
				or content like '%'||'1'||'%'
				or title like '%'||'1'||'%' )
				
				
				
				select * from TBL_BOARD b, TBL_MEMBER m where b.writer = m.userId
				and
				 'hi' like '%'||'asdf'||'%'
				 
				 
				 select * from TBL_BOARD b, tbl_member m where b.writer = m.userId
			and
				(userName like '%'||'hi'||'%'
				or content like '%'||'hi'||'%'
				or title like '%'||'hi'||'%' )
				order by bno desc, regdate desc 