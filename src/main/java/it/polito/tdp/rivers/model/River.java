package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class River {
	private int id;
	private String name;
	private double flowAvg;
	private List<Flow> flows;
	private LocalDate firstFlowDate;
	private LocalDate lastFlowDate;
	private int totalMeasureament;
	public boolean inizializzato = false;
	
	public River(int id) {
		this.id = id;
	}
	public River() {
		
	}

	public River(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public LocalDate getFirstFlowDate() {
		return firstFlowDate;
	}
	public void setFirstFlowDate(LocalDate firstFlowDate) {
		this.firstFlowDate = firstFlowDate;
	}
	public LocalDate getLastFlowDate() {
		return lastFlowDate;
	}
	public void setLastFlowDate(LocalDate lastFlowDate) {
		this.lastFlowDate = lastFlowDate;
	}
	public int getTotalMeasureament() {
		return totalMeasureament;
	}
	public void setTotalMeasureament(int totalMeasureament) {
		this.totalMeasureament = totalMeasureament;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getFlowAvg() {
		return flowAvg;
	}

	public void setFlowAvg(double flowAvg) {
		this.flowAvg = flowAvg;
	}

	public void setFlows(List<Flow> flows) {
		this.flows = flows;
	}

	public List<Flow> getFlows() {
		if (flows == null)
			flows = new ArrayList<Flow>();
		return flows;
	}
	
	

	@Override
	public String toString() {
		return name;
	}
	
	public void createInfoFiume() {
		LinkedList<Flow> valori = new LinkedList<Flow>(this.getFlows());
		this.firstFlowDate = valori.getFirst().getDay();
		this.lastFlowDate = valori.getLast().getDay();
		this.totalMeasureament = valori.size();
		double sum = 0;
		for(Flow f: valori) {
			sum+=f.getFlow();
		}
		this.flowAvg = sum/this.totalMeasureament;
		this.inizializzato = true;
		
		
		
		
	}
	
	public String descriviti() {
		return this.firstFlowDate + " " + this.lastFlowDate + " " + this.totalMeasureament + " " + this.flowAvg;
		
		
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		River other = (River) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
