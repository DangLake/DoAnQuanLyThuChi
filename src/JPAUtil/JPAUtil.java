package JPAUtil;
import javax.persistence.*;
public class JPAUtil {
	private static EntityManagerFactory ef=null;
	public static EntityManager getManager() {
		if(ef==null) {
			ef=Persistence.createEntityManagerFactory("giang");
		}
		return ef.createEntityManager();
	}
}
