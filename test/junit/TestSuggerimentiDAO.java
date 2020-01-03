package junit;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bean.SuggerimentiBean;
import dao.SuggerimentiDAO;

public class TestSuggerimentiDAO {

	private SuggerimentiDAO tester=new SuggerimentiDAO();
	private SuggerimentiBean s;
	
	@Before
	public void setUp() throws SQLException {
		s=new SuggerimentiBean(0, "recensioni non funzionanti", " jvhsdkvbhjsvbjs vbsvbsdk vhsdkjvbhdj fv svsd fsvd fsd bsdfb sdfb dsfbds b s dfb sdbdfb sdb sdbd", "tanucc");
		tester.doSave(s);
		ArrayList<SuggerimentiBean>find=new ArrayList<SuggerimentiBean>();
		find=tester.doRetrieveAll();
		s.setId(find.get(find.size()-1).getId());
	}
	
	@After
	public void tearDown() throws SQLException {
		   tester.doDelete(s.getId());
	}
	
	@Test
	public void testDoSave() {
		SuggerimentiBean s2=tester.doRetrieveByKey(s.getId());
		assertEquals("Result", s,s2);
	}

	@Test
	public void testDoRetrieveByKey() {
		assertEquals("Result", s, tester.doRetrieveByKey(s.getId()));
	}

	@Test
	public void testDoDelete() throws SQLException {
		assertEquals("Result",true,tester.doDelete(s.getId()));
	}

	@Test
	public void testDoUpdate() throws SQLException {
		s.setTesto("questo e' il testo");
		s.setTitolo("Questo e' il titolo");
		tester.doUpdate(s);
		assertEquals("Result", s, tester.doRetrieveByKey(s.getId()));
	}

	@Test
	public void testDoRetrieveAll() throws SQLException {
		ArrayList<SuggerimentiBean> list=new ArrayList<SuggerimentiBean>();
		SuggerimentiBean s2=new SuggerimentiBean(22, "recensioni non funzionanti", " jvhsdkvbhjsvbjs vbsvbsdk vhsdkjvbhdj fv svsd fsvd fsd bsdfb sdfb dsfbds b s dfb sdbdfb sdb sdbd", "tanucc");
		list.add(s2);
		s2=new SuggerimentiBean(24, "problema home", " jvhsdkvbhjsvbjs vbsvbsdk vhsdkjvbhdj fv svsd fsvd fsd bsdfb sdfb dsfbds b s dfb sdbdfb sdb sdbd ds fasg ag ds gsdhsh sdh shbsv dfsb sdfb sdfb sfdbfsdbfdsb sfdbsfbsdfbsdfbsdf b", "tanucc");
		list.add(s2);
		s2=new SuggerimentiBean(26, "pizzerie non presenti", "non ci sono pizzerie sul sito", "tanucc");
		list.add(s2);
		s2=new SuggerimentiBean(27, "ciao", "il problema è questo", "tanucc");
		list.add(s2);
		list.add(s);
		assertEquals("Result", list, tester.doRetrieveAll());
	}

}
