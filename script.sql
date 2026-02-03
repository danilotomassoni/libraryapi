select b1_0.id,a1_0.id,a1_0.date_birth,a1_0.name,a1_0.nationality,b1_0.gender,b1_0.isbn,b1_0.price,b1_0.publication_date,b1_0.title 
from books b1_0 
left join authors a1_0 on a1_0.id=b1_0.id_author 
where b1_0.id=?