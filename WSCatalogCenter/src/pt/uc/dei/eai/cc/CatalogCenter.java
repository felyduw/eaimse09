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
import pt.uc.dei.eai.data.HibernateUtil;

@WebService
public class CatalogCenter implements ICatalogCenter {

	//@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WSCameraSupplier?wsdl")
	//static CameraSupplierService CameraService;

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
	public List<Camera> addCamerasToDB(String searchTerms) {
		//FIXME CALL PROCESS ORCHESTRATOR
		List<Camera> ls = new ArrayList<Camera>();
		/*CameraSupplier cs = CameraService.getCameraSupplierPort();

		List<pt.uc.dei.eai.cs.Camera> tmp = cs.getCameras(searchTerms);

		Session tsx = HibernateUtil.beginTransaction();
		for (pt.uc.dei.eai.cs.Camera cam : tmp) {
			Camera camToAdd = new Camera(cam);
			ls.add(camToAdd);
			tsx.saveOrUpdate(camToAdd);
		}
		HibernateUtil.commitTransaction();
*/		
		return ls;
	}

}
