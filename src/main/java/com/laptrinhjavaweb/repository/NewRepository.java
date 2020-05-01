package com.laptrinhjavaweb.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.laptrinhjavaweb.entity.NewEntity;
/* JpaRepository<t1, t2> (t1: Entity dang lam viec / t2: Kieu du lieu cua khoa chinh)
 * 
 */
public interface NewRepository extends JpaRepository<NewEntity, Long>{
	List<NewEntity> findByTitleContainingIgnoreCase(Pageable pageable,String searchValue);
	List<NewEntity> findByCategoryId(Long categoryId);
}
