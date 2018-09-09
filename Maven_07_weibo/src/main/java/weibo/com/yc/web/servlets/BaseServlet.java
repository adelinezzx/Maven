package weibo.com.yc.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


//拦jie请求，从中间取出op参数.
//从request中取出参数值，包装成  T
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected String op;                  
	protected String contentType;        
	protected Map<String,String[]> map;  
	protected ServletContext application;
	protected HttpSession session;
	
	public BaseServlet() {
		super();
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		application=arg0.getSession().getServletContext();
		session=arg0.getSession();
		contentType=arg0.getContentType();
		//如果是contentType值为:  multipart/form-data  ,说明为文件上传的请求.
		if(  contentType==null||   !contentType.startsWith(  "multipart/form-data")   ){
			//普通的请求
			op = arg0.getParameter("op");
			map=arg0.getParameterMap();
		}else{
			//文件上传的情况
//			PageContext pageContext = JspFactory .getDefaultFactory().getPageContext(this, arg0, arg1, null, true, 8192, true);
//			UploadUtil uu=new UploadUtil();
//			try {
//				map=uu.upload(    pageContext  );
//				if(  map.get("op")!=null&& map.get("op").length>0){
//					op=map.get("op")[0];
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		super.service(arg0, arg1);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void writeJson(HttpServletResponse response, Object obj)
			throws IOException {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String jsonString = gson.toJson(obj);
		out.println(jsonString);
		out.flush();
		out.close();
	}
	
	private <T> void extractSingleParameterType(T t, Method method,
			String[] values, Class parameterType)
			throws IllegalAccessException, InvocationTargetException,
			ParseException {
		String s = values[0];
		String parameterTypeName = parameterType.getName();  
		if("int".equals(parameterTypeName) || "java.lang.Integer".equals(parameterTypeName)){
			method.invoke(t, Integer.parseInt(s));
		}else if("double".equals(parameterTypeName) || "java.lang.Double".equals(parameterTypeName)){
			method.invoke(t, Double.parseDouble(s));
		}else if("float".equals(parameterTypeName) || "java.lang.Float".equals(parameterTypeName)){
			method.invoke(t, Float.parseFloat(s));
		}else if("byte".equals(parameterTypeName) || "java.lang.Byte".equals(parameterTypeName)){
			method.invoke(t, Byte.parseByte(s));
		}else if("java.util.Date".equals(parameterTypeName)){    //  setXX( Date d )
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			method.invoke(t, sdf.parse(s));
		}else{
			method.invoke(t, s);
		}
	}
	
	private <T> void extractArrayParameterType(T t, Method method,
			String[] values, Class parameterType)
			throws IllegalAccessException, InvocationTargetException,
			ParseException {
		String parameterTypeName = parameterType.getName();  //   [I  => int[]     [Ljava.lange.Integer;
		if("[I".equals(parameterTypeName)){
			int[] intArray = new int[values.length];
			for(int i = 0; i < intArray.length; i++){
				intArray[i] = Integer.parseInt(values[i]);
			}
			method.invoke(t, intArray);
		}else if("[Ljava.lang.Integer;".equals(parameterTypeName)){
			Integer[] intArray = new Integer[values.length];
			for(int i = 0; i < intArray.length; i++){
				intArray[i] = Integer.parseInt(values[i]);
			}
			method.invoke(t, (Object)intArray);
		}else if("[D".equals(parameterTypeName)){
			double[] doubleArray = new double[values.length];
			for(int i = 0; i < doubleArray.length; i++){
				doubleArray[i] = Double.parseDouble(values[i]);
			}
			method.invoke(t, doubleArray);
		}else if("[Ljava.lang.Double;".equals(parameterTypeName)){
			Double[] doubleArray = new Double[values.length];
			for(int i = 0; i < doubleArray.length; i++){
				doubleArray[i] = Double.parseDouble(values[i]);
			}
			method.invoke(t, (Object)doubleArray);
		}else if("[F".equals(parameterTypeName)){
			float[] floatArray = new float[values.length];
			for(int i = 0; i < floatArray.length; i++){
				floatArray[i] = Float.parseFloat(values[i]);
			}
			method.invoke(t, floatArray);
		}else if("[Ljava.lang.Float;".equals(parameterTypeName)){
			Float[] floatArray = new Float[values.length];
			for(int i = 0; i < floatArray.length; i++){
				floatArray[i] = Float.parseFloat(values[i]);
			}
			method.invoke(t, (Object)floatArray);
		}else if("[B".equals(parameterTypeName)){
			byte[] byteArray = new byte[values.length];
			for (int i = 0; i < byteArray.length; i++) {
				byteArray[i] = Byte.parseByte(values[i]);
			}
			method.invoke(t, byteArray);
		}else if("[Ljava.lang.Byte;".equals(parameterTypeName)){
			Byte[] byteArray = new Byte[values.length];
			for (int i = 0; i < byteArray.length; i++) {
				byteArray[i] = Byte.parseByte(values[i]);
			}
			method.invoke(t, (Object)byteArray);
		}else if("[Ljava.lang.String;".equals(parameterTypeName)){
			method.invoke(t, (Object)values);
		}
	}
	
	protected <T> T getReqParamObj(Map<String,String[]> map, Class<T> clazz) {
		T t = null;
		map = parseRequestMap(map);
		try {
			t = clazz.newInstance();  
			Method[] ms = clazz.getMethods(); 
			for (Method method : ms) {
				String methodName = method.getName(); 
				if(    map.containsKey(methodName)  ){
					String[] values =map.get(methodName);
					Class parameterType = method.getParameterTypes()[0];  
					//兴趣爱好这种[]类型 的参数如何解析
					if(parameterType.getName().startsWith("[")){
						extractArrayParameterType(t, method, values,									parameterType);
					}else{
						//例 如用户名这种单值的参数
						extractSingleParameterType(t, method, values,									parameterType);
					}
				}
			}
		} catch (
		Exception e) {
			throw new RuntimeException(e);
		} 
		return t;
	}
	
	protected <T> T getReqParamObj(HttpServletRequest request, Class<T> clazz) {
		T t = null;
		Map<String, String[]> map = request.getParameterMap(); 
		return getReqParamObj( map, clazz);
	}
	
	
	private Map<String, String[]> parseRequestMap(Map<String, String[]> map) {
		Map<String, String[]> newMap = new HashMap<String, String[]>();
		for(Map.Entry<String, String[]> entry : map.entrySet()){
			String key = entry.getKey();
			key = "set" + key.substring(0,1).toUpperCase() + key.substring(1);
			newMap.put(key, entry.getValue());
		}
		return newMap;
	}
	
	
	
	

}
