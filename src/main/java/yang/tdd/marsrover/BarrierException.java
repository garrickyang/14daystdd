package yang.tdd.marsrover;

public class BarrierException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BarrierException() {
		super();
	}
	public BarrierException(String errorMessage){
		super(errorMessage);
	}
}
