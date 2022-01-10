package bankmanagementsystem;


/*
 * ChargesTransactionFee Interface
 * implemented on Account class when account includes transaction fees
 */
public interface ChargesTransactionFee {
	
	double getTransactionFee();
	
	void chargeFee();

	boolean isFeeCharged();
}
