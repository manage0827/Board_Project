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

 
 where tbl_board.writer = tbl_member.userId
			and
				where writer like '%'||#{keyword}||'%'
				or content like '%'||#{keyword}||'%'
				or title like '%'||#{keyword}||'%'
				
				
				where tbl_board.writer == tbl_member.userId and ${searchOption} like '%'||#{keyword}||'%'
				
				select count(*) from tbl_board b, tbl_member m
				select * from b