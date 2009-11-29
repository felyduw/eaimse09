package pt.uc.dei.eai.cc;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.common.CameraSearch;
import pt.uc.dei.eai.data.HibernateUtil;

@WebService
public class CatalogCenter implements ICatalogCenter {

	@Override
	@WebMethod
	public Camera getCamera(Integer cameraId) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Camera.class);
		criteria.add(Restrictions.eq("id", cameraId));

		Camera result = (Camera) criteria.uniqueResult();
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
	@WebMethod
	public List<Camera> searchCameras(String searchTerms) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Camera.class);

		if (searchTerms != null && !searchTerms.isEmpty()) {
			criteria.add(Restrictions.like("model", searchTerms,
					MatchMode.ANYWHERE));
		}

		@SuppressWarnings("unchecked")
		List<Camera> result = (List<Camera>) criteria.list();
		HibernateUtil.commitTransaction();

		return result;
	}

	@Override
	@WebMethod
	public List<Camera> addCamerasToDB(List<CameraSearch> cameras) {

		List<Camera> ls = new ArrayList<Camera>();
		
		Session tsx = HibernateUtil.beginTransaction();
		for (CameraSearch cam : cameras) {
			Camera camToAdd = new Camera();
			camToAdd.setModel(cam.getModel());
			camToAdd.setPrice(cam.getPrice());
			ls.add(camToAdd);
			camToAdd.setId((Integer) tsx.save(camToAdd));
		}
		HibernateUtil.commitTransaction();
				
		return ls;
	}

	@WebMethod
	public void recreateDB() {
		HibernateUtil.recreateDatabase();
	}
}
