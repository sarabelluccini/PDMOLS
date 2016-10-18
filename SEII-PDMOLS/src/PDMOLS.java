import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class PDMOLS {

	public static void main(String[] args) {
		// Test Libro 1°
		System.out.println("Primo TEST");
		PDMOLSIMPL test1 = new PDMOLSIMPL();
		test1.insertFactor("b1", new int[] { 1, 2, 3 });
		test1.insertFactor("b2", new int[] { 1, 2, 3, 4 });
		test1.insertFactor("b3", new int[] { 1, 2, 3 });
		test1.computePDMOLSTable();
		
		// Test Libro 2°
		System.out.println("Secondo TEST");
		PDMOLSIMPL test2 = new PDMOLSIMPL();
		test2.insertFactor("b1", new int[] { 1, 2, 3, 4 });
		test2.insertFactor("b2", new int[] { 1, 2, 3, 4 });
		test2.insertFactor("b3", new int[] { 1, 2, 3, 4 });
		test2.insertFactor("b4", new int[] { 1, 2, 3, 4 });
		test2.computePDMOLSTable();

		/*System.out.println("Quinto TEST");
		PDMOLSIMPL test5 = new PDMOLSIMPL();
		test5.insertFactor("b1", new int[] { 1, 2, 3, 4 });
		test5.insertFactor("b2", new int[] { 1, 2, 3, 4 });
		test5.insertFactor("b3", new int[] { 1, 2, 3, 4 });
		test5.insertFactor("b4", new int[] { 1, 2});
		test5.insertFactor("b5", new int[] { 1, 2, 3});
		test5.computePDMOLSTable();*/
		
		// Check : Impossibile calcolare MOLS PER k=2 O k=6
		/*System.out.println("Terzo TEST");
		PDMOLSIMPL test3 = new PDMOLSIMPL();
		test3.insertFactor("b1", new int[] { 1, 2 });
		test3.insertFactor("b2", new int[] { 1, 2 });
		test3.computePDMOLSTable();*/
		/*System.out.println("Quarto TEST");
		PDMOLSIMPL test4 = new PDMOLSIMPL();
		test4.insertFactor("b1", new int[] { 1, 2, 3, 4, 5, 6 });
		test4.insertFactor("b2", new int[] { 1, 2, 3, 4, 5, 6 });
		test4.insertFactor("b3", new int[] { 1, 2, 3, 4, 5, 6 });
		test4.insertFactor("b4", new int[] { 1, 2 });
		test4.insertFactor("b5", new int[] { 1, 2 });
		test4.insertFactor("b6", new int[] { 1, 2 });
		test4.computePDMOLSTable();*/
		
		System.exit(0);

	}

}
