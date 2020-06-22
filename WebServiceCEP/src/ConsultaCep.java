import WebService.ClienteWS;
import Visual.VisualCrud;
import bd.dbo.InfoCidade;
import bd.dbo.Logradouro;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsultaCep {

	public static void main(String[] args) {

		try {
			VisualCrud j = new VisualCrud();
			System.out.println(j);
		}
		catch(Exception erro){
			
			System.out.println(erro);
		}
	}

}
