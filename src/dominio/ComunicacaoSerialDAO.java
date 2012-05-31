package dominio;
import dominio.InterfaceComunicaoSerial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import modelo.Dados;


public class ComunicacaoSerialDAO implements InterfaceComunicaoSerial {

	@Override
	public void salvar(Dados dados) {
		System.out.println("Entrou com"+ dados.getDados());
		
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();

		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(dados);
		System.out.println("Dado gravado com sucesso.");
		tx.commit();
	
	}

	
	
}
