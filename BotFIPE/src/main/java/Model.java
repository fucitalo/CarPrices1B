import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.pengrad.telegrambot.model.Update;

public class Model implements Subject{

	private List<Observer> observers = new LinkedList<Observer>();

	private List<Carro> carros = new LinkedList<Carro>();

	private static Model uniqueInstance;

	private int n=0;

	private String marca, modelo, ano;
	
	private Model(){}

	public static Model getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new Model();
		}
		return uniqueInstance;
	}

	public void registerObserver(Observer observer){
		observers.add(observer);
	}

	public void notifyObservers(long chatId, String studentsData){
		for(Observer observer:observers){
			observer.update(chatId, studentsData);
		}
	}

	public void addStudent(Carro carro){
		this.carros.add(carro);
	}

	public void searchMarca(Update update) throws IOException{
		String carData = null;
		
		for(Carro carro: carros){		
			carData = Main.pesquisa(update.message().text());
			System.out.println(carData);
			//carData = carro.getCodigo();			
		}

		if(!carData.equals("-1")){
			this.notifyObservers(update.message().chat().id(), carData);
		} else {
			this.notifyObservers(update.message().chat().id(), "Marca não encontrada");
		}

	}

	public void searchModelo(Update update) throws IOException{
		String allData = null;
		n++;
		if(n==1){
			marca = update.message().text();
		}else if(n==2){
			modelo = update.message().text();
		}else if(n==3){
			ano = update.message().text();
				
			//System.out.println(marca+"x" + modelo+"x" + ano);
			for(Carro carro: carros){	
				n=0;
				allData = Main.pesquisaModelo(marca, modelo, ano);
				//System.out.println(allData);				
			}

			if(allData != null){
				this.notifyObservers(update.message().chat().id(), allData);
			} else {
				this.notifyObservers(update.message().chat().id(), "Veículo não encontrado");
			}	
		}
	}

}
