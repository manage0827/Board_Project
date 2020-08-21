create table tbl_member
  	  (userId varchar2(20),
  	  userPw varchar2(20),
  	  userName varchar2(20),
  	  userEmail varchar2(30),
  	  userRegdate date default sysdate,
  	  userUpdatedate date default sysdate) 
  select username As userName from tbl_member
  
 select count(*) from tbl_board b, tbl_member m
 
 select * from tbl_board;
select * from TBL_MEMBER;
 
select b.bno, b.title,b.content, b.regdate, ,b.viewcnt, m.userName from tbl_board b,tbl_member m where b.writer = m.userId(+)

select  a.bno, a.title,a.content, a.regdate, ,a.viewcnt, b.userName from tbl_board a, tbl_member b where a.writer = b.userId
insert into tbl_board(writer,content) values('admin','내용있음')
insert into tbl_board 
select * from TBL_BOARD a, tbl_member b where a.writer = b.userId
select * from tbl_board
delete from tbl_board
select b.bno, b.title, b.content, b.regdate, b.writer ,b.viewcnt, m.userId, m.userName from tbl_board b left outer join TBL_MEMBER m on b.writer = m.userid
select *  from tbl_board b left outer join TBL_MEMBER m on b.writer = m.userId

select count(*) from tbl_board  b, tbl_member m


select * from (select rownum as rn, a.* from (select rownum, bno,title,content, b.regdate, viewcnt, userName 
from TBL_BOARD b, tbl_member m 
where b.writer = m.userId
			and
				(userName	 like '%'||'hi'||'%'
				or content like '%'||'hi'||'%'
				or title like '%'||'hi'||'%' ) order by bno desc, regdate desc) A ) where rn between '1' and '2'
				
				
select * 
from (select rownum as rn, A.* 
		from (select rownum, bno,title,content, b.regdate, viewcnt, userName 
					from TBL_BOARD b, tbl_member m
					where b.writer = m.userId
						and
				(userName like '%'||'hi'||'%'
					or content like '%'||'hi'||'%'
					or title like '%'||'hi'||'%')
		order by bno desc, regdate desc) A) where rn between 1 and 10

		select * from tbl_reply
		select userId from tbl_member 
		
		insert into tbl_member(userId, userPw, userName) values ('admi', 'admi','admi')
		
		
		
	create table tbl_reply(
	rno number,
	bno number,
	replytext varchar2(200),
	replyer varchar2(20),
	userName varchar(20),
	regdate date default sysdate,
	updatedate date default sysdate
	)
  
	tbl_board 에서 writer를 검색 -> 
	select * from 
			(select rownum as rn, A.* 
				from(select r.rno, r.bno, r.replytext, r.replyer, r.secretReply, r.regdate, r.updatedate, m.userName,
						(select writer from tbl_board where bno = r.bno) as writer
					from tbl_reply r, tbl_member m
					where r.replyer = m.userId
					and r.bno = 1
					order by rno 
		) A ) where rn between 1 and 10
		
		
		update tbl_reply set secretReply = 'y'
		select rownum, bno, title, b.regdate, viewcnt, m.userName,
		(select secretReply from tbl_reply where bno=b.bno) as recnt
		from tbl_board b, tbl_member m