package pt.uc.dei.eai.lpco;

import java.util.List;

import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import pt.uc.dei.eai.common.Camera;
import pt.uc.dei.eai.cs.CameraSupplier;
import pt.uc.dei.eai.cs.CameraSupplierService;
import pt.uc.dei.eai.data.HibernateUtil;

/**
 * Session Bean implementation class CatalogCenterBean
 */
@Stateless
public class CatalogCenterBean implements CatalogCenterBeanRemote,
		CatalogCenterBeanLocal {
	
	@WebServiceRef(wsdlLocation = "http://127.0.0.1:8080/WSCameraSupplier?wsdl")
	static CameraSupplierService CameraService;

	@Override
	public Camera getCamera(Integer cameraId) {
		Session session = HibernateUtil.beginTransaction();
		Criteria criteria = session.createCriteria(Camera.class);
		criteria.add(Restrictions.eq("id", cameraId));

		Camera result = (Camera) criteria.uniqueResult();
		HibernateUtil.commitTransaction();
		return result;
	}

	@Override
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

		if (result.isEmpty()) {
			CameraSupplier cs = CameraService.getCameraSupplierPort();

			List<pt.uc.dei.eai.cs.Camera> tmp = cs.getCameras(searchTerms);

			Session tsx = HibernateUtil.beginTransaction();
			for (pt.uc.dei.eai.cs.Camera cam : tmp) {
				Camera camToAdd = new Camera(cam);
				result.add(camToAdd);
				tsx.saveOrUpdate(camToAdd);
			}
			HibernateUtil.commitTransaction();
		}

		return result;
	}

}
