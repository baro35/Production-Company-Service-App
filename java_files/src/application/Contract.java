package application;

import java.util.ArrayList;

public class Contract {
	private int contractID;
	
	
	private Date validFrom;
	private Date validUntil;
	private int contractActorID;
	private int contractProducerID;
	private static String[] headers = {"Valid From","Valid Until","Contract From Person ID","Contract To Person ID"};
	private static int contractIDcounter;
	
	public Contract(String[] contractInfo) {
		contractID = contractIDcounter;
		contractIDcounter++;
		this.validFrom = new Date(contractInfo[0].split(":")[0],contractInfo[0].split(":")[1],contractInfo[0].split(":")[2]);
		this.validUntil = new Date(contractInfo[1].split(":")[0],contractInfo[1].split(":")[1],contractInfo[1].split(":")[2]);
		this.contractActorID = Integer.parseInt(contractInfo[2]);
		this.contractProducerID = Integer.parseInt(contractInfo[3]);

	}
	
	public String toTextForWriteFile() {
		return validFrom.toString() + ";" + validUntil.toString() + ";" + contractActorID + ";" +  contractProducerID + "\n";
	}
	
	public boolean deleteContract() {return false;}

	public String[] tableRow() {
		String[] st = {validFrom.toString(),validUntil.toString(),Integer.toString(contractActorID),Integer.toString(contractProducerID)};
        return st;
    }
	


	public int getContractActorID() {
		return contractActorID;
	}

	public void setContractActorID(int contractActorID) {
		this.contractActorID = contractActorID;
	}

	public int getContractProducerID() {
		return contractProducerID;
	}

	public void setContractProducerID(int contractProducerID) {
		this.contractProducerID = contractProducerID;
	}

	public static String[] getHeaders() {
		return headers;
	}

	public int getContractID() {
		return contractID;
	}
	public void setContractID(int contractID) {
		this.contractID = contractID;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidUntil() {
		return validUntil;
	}
	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public static int getContractIDcounter() {
		return contractIDcounter;
	}

	public static void setContractIDcounter(int contractIDcounter) {
		Contract.contractIDcounter = contractIDcounter;
	}
	
}
