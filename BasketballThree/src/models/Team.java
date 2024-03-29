package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.Gender;
import enums.PlayerPosition;
import enums.TypeState;
import enums.TypeDocument;
import exceptions.DocumentNotFoundException;
import utilities.Utilities;

public class Team implements OperatorsLigueInterface{
	
	public static final int AUTOGENERATED= 115866200;
	private String name; 
	private String identification; 
	private int id; 
	private TypeState state;
	private List<Player> playerList;
	
	public Team(int id, String name, String identification, TypeState state) {
		this.name = name;
		this.identification = identification;
		this.id = id;
		this.state = state;
		playerList = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public TypeState getState() {
		return state;
	}

	public void setState(TypeState state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void add(Person person) {
		playerList.add((Player) person);
	}
	
	public static Player createPlayers(String numberPlayer, float weight, float height, PlayerPosition playerPosition, String firstName,
			String lastName, TypeDocument typeDocument, String documentNumber, LocalDate date, Gender gender) {
		return new Player(numberPlayer, weight, height, playerPosition, firstName, lastName, typeDocument, documentNumber, date, gender, generateID());
	}
	
	public ArrayList<Object[]> toMatrixVector() {
		ArrayList<Object[]> datas = new ArrayList<Object[]>();
		for (Player player : playerList) {
			datas.add( Utilities.concatObjectArrays( toObjectVector(), player.toObjectVector()) );
		}
		return datas;
	}
	
	public boolean validatePlayer(int id) {
		boolean exist = false; 
		for (Player player : playerList) {
			if(player.getId() ==id) {
				exist = true;
			}
		}
		return exist;
	}

	public Player search(String documentNumber) throws DocumentNotFoundException{
		for (Player player : playerList) {
			if(player.getDocumentNumber().equals(documentNumber)) {
				return player;
			}
		}
		throw new DocumentNotFoundException();
	}
	
	public void delete(String documentNumber) throws DocumentNotFoundException {
		playerList.remove(search(documentNumber));
	}

	public void replace(int pos, Person person) {
		playerList.set(pos, (Player) person);
	}

	public void editTypeDocument(String documentNumber, TypeDocument newDocumentType) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setTypeDocument(newDocumentType);
			}
		}
	}
	
	public void editDocumentNumber(String documentNumber, String newNumberDocument) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setDocumentNumber(newNumberDocument);
			}
		}
	}
	
	public void editFirstName(String documentNumber, String name) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setFirstName(name);
			}
		}
	}
	
	public void editSurname(String documentNumber, String surname) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setLastName(surname);
			}
		}
	}
	
	public void editGender(String documentNumber, Gender genderChange) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setGender(genderChange);
			}
		}
	}
	
	public void editWeight(String documentNumber, float weight) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setWeight(weight);
			}
		}
	}
	
	public void editHeight(String documentNumber, float height) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setHeight(height);
			}
		}
	}
	
	public static int generateID() {
		int aux = AUTOGENERATED;
		return ++aux;
	}
	
	public short calculateNumberOfWomen() {
		short quantity = 0;
		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getGender() == Gender.FEMALE) {
				quantity++;
			}
		}
		return quantity;
	}
	
	public int calculatePercentNumberOfWomen() {
		short numberOfWomen = calculateNumberOfWomen();
		int average = (numberOfWomen * 100)/ playerList.size();
		return average;
	}
	
	public short calculateNumberOfMen() {
		short quantity = 0;
		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getGender() == Gender.MALE) {
				quantity++;
			}
		}
		return quantity;
	}
	
	public int calculatePercentNumberOfMen() {
		short numberOfMen = calculateNumberOfMen();
		int average = (numberOfMen * 100)/ playerList.size();
		return average;
	}
	
	public double[] convertPercentageOfPlayerToVector() {
		return new double[] { calculatePercentNumberOfMen(), calculatePercentNumberOfMen()};
	}
	
	public ArrayList<Player> seeListWomen(){
		ArrayList <Player> listAuxWomen = new ArrayList<>();
		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getGender() == Gender.FEMALE) {
				listAuxWomen.add(playerList.get(i));
			}
		}
		return listAuxWomen;
	}
	
	public ArrayList<Player> seeListMen(){
		ArrayList <Player> listAuxMen = new ArrayList<>();
		for (int i = 0; i < playerList.size(); i++) {
			if(playerList.get(i).getGender() == Gender.MALE) {
				listAuxMen.add(playerList.get(i));
			}
		}
		return listAuxMen;
	}
	
	public void editBirthdate(String documentNumber, LocalDate birthdate) {
		for (int i = 0; i < playerList.size(); i++) {
			if (documentNumber.equals(playerList.get(i).getDocumentNumber())) {
				playerList.get(i).setBirthdate(birthdate);;
			}
		}
		
	}

	public Object[] toObjectVector() {
		return new Object[] { name, identification, state.getState(), id};
	}

}
