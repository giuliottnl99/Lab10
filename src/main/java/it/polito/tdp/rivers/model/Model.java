package it.polito.tdp.rivers.model;

import java.awt.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	
	
	/*
	 * Algoritmo per la risoluzione del primo problema
	 * Prima di tutto creo la lista con tutti i fiumi e ogni fiume si riferisce proprio
	 * all'oggetto fiume. Per farlo interrogo il DB.
	 * Scelto il fiume, uso la funzione toString() del fiume creato.
	 * 
	 * Dunque nel model uso due funzioni, che saranno chiamate dall'FXMLController:
	 * caricaFiumi(), che si appoggia alla classe RisersDAO per prenderli fiumi=DAO.caricaFiumi. Questa
	 * funzione viene chiamata nel main, durante l'inizializzazione. Il model però viene creato prima nel main.
	 * La funzione caricaFiumi deve creare un Fiume, il fiume deve essere definito
	 * I fiumi dovranno poi essere caricati anche nella lista. Per farlo uso getFiumi
	 * 
	 * Il problema del fiume è che deve avere anche la lista dei flow. Per trovarli devo interrogare il DAO
	 * 
	 * descriviFiume(Fiume f), che porta la descrizione di F.
	 * F avrà quindi una classe descriviti.
	 */
	
	private LinkedList<River> fiumi = new LinkedList<River>();
	double QMedio;
	int numeroDisservizi;
	
	
	
	public void caricaFiumi() {
		RiversDAO rdao = new RiversDAO();
		fiumi =  (LinkedList<River>) rdao.getAllRivers();
	}
	
	/*
	 * Quando il fiume viene scelto vi aggiungo a quel fiume tutte le sue rive
	 * 
	 */
	
	public void aggiungiRive(River river) {
		RiversDAO rdao = new RiversDAO();
		
		river.setFlows(rdao.getAllFlowOfRiver(river));
	}
	public boolean Startsimulate(double k, River f) {
		double Qmax = k*30*f.getFlowAvg();
		double cAttuale = Qmax/2;
		double consumoMin = f.getFlowAvg()*0.8;
		double consumoMax = consumoMin*10;
		double cTot=0;
		
		int nRilevazioni = 0;
		this.numeroDisservizi=0;
		
		
		//Verifico che k sia valido
		if(k<=0)
			return false;
		//Per ogni giorno, devo togliere l'acqua in base al caso e vedere se c'è un flowIn
//		LocalDate ld = f.getFirstFlowDate();
//		LinkedList<LocalDate> elencoDate = new LinkedList<LocalDate>();
		for(Flow flow: f.getFlows()) {
			/*
			 * Per ogni flow prendo la data e il valore
			 * Sulla base di questi due valori aggiorno i dati globali
			 */
			cAttuale += flow.getFlow();
			nRilevazioni++;
			//A questo punto tolgo il flow in modo casuale
			if(cAttuale<=consumoMin) {
				cAttuale = 0;
				this.numeroDisservizi++;
			}
			else {
				double casual = Math.random();
				if(casual<=0.05) {
					cAttuale -= consumoMax;
				}
				else {
					cAttuale -= consumoMin;
				}
				if(cAttuale >= Qmax) {
					cAttuale = Qmax;
				}
			}
			
			cTot +=cAttuale;
		}
		this.QMedio = cTot/nRilevazioni;
		
		
		
		return true;
	}
	
	public String getResultSimulation() {
		String result="";
		result+="Numero di giorni in cui non si è potuta garantire l'erogazione minima: " +this.numeroDisservizi;
		result+= "\n Occupazione media del bacino nel corso della simulazione: " + this.QMedio;
		return result;
	}
	
	
	

	public LinkedList<River> getFiumi() {
		return fiumi;
	}

	public void setFiumi(LinkedList<River> fiumi) {
		this.fiumi = fiumi;
	}
	
	

	
	
	
}
