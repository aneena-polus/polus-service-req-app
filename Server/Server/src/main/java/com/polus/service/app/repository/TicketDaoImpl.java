package com.polus.service.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.polus.service.app.entities.TicketStatus;
import com.polus.service.app.entities.TicketType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository 
public class TicketDaoImpl implements TicketDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public TicketStatus findByStatusName(String statusName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketStatus> criteriaQuery = criteriaBuilder.createQuery(TicketStatus.class);
		Root<TicketStatus> root = criteriaQuery.from(TicketStatus.class);
		Predicate namePredicate = criteriaBuilder.like(root.get("statusName"), statusName);
		criteriaQuery.where(namePredicate);
		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	
	@Override
	public TicketType findByTypeName(String typeName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketType> cq = cb.createQuery(TicketType.class);
		//SELECT * FROM TICKET_TYPE
		Root<TicketType> root = cq.from(TicketType.class);
		//ticket_type LIKE 'Hardware support'
		Predicate namePredicate = cb.like(root.get("ticketType"), typeName);
		//WHERE clause
		cq.where(namePredicate);
		return entityManager.createQuery(cq).getSingleResult();
	}
}
