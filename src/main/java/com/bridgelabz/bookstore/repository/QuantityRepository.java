package com.bridgelabz.bookstore.repository;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.bookstore.entity.CustomerInformation;
import com.bridgelabz.bookstore.entity.QuantityEntity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
@Repository
public interface QuantityRepository  extends JpaRepository<QuantityEntity, Long> 
{

	@Modifying
	@Query( value= "insert into Quantity_entity(quantity,book_id) values(?,?)",nativeQuery=true)
	void create(int quantity,long bookid);
	
	@Modifying
	@Query( value= "insert into cart_quantity(cart_id,quantity_id )values(?,?)",nativeQuery=true)
	void save(long cart_id, long quantity_id);
	
	@Modifying
	@Query( value = "select * from Quantity_entity  where quantity_id = :quantity_id", nativeQuery = true)
	QuantityEntity  getquantId( long quantity_id);
	
	
	

	
	
}
