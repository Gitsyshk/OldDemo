package trainApp.http;

public class PostDataProvider {
	private static String LX = "lx";// ��ͨ��ѯ00;ѧ�������ѯ0X��ũ�������ѯ0M
	public static String LX_VALUE_00 = "00";
	public static String LX_VALUE_0X = "0X";
	public static String LX_VALUE_0M = "0M";

	// ��ѯ����
	private static String NMONTH3 = "nmonth3";
	private static String NMONTH3_NEW_VALUE = "nmonth3_new_value";
	private static String NDAY3 = "nday3";
	private static String NDAY3_NEW_VALUE = "nday3_new_value";

	// ��վ�뵽վ
	private static String STARTSTATION_TICKETLEFT = "startStation_ticketLeft";
	private static String STARTSTATION_TICKETLEFT_NEW_VALUE = "startStation_ticketLeft_new_value";
	private static String ARRIVESTATION_TICKETLEFT = "arriveStation_ticketLeft";
	private static String ARRIVESTATION_TICKETLEFT_NEW_VALUE = "arriveStation_ticketLeft_new_value";
	// ����
	private static String TRAINCODE = "trainCode";
	private static String TRAINCODE_NEW_VALUE = "trainCode_new_value";

	// ȫ��1��ʼ��3���յ�4��ʼ���յ�5����·6��
	private static String RFLAG = "rFlag";
	/**
	 * ɸѡ��ѯ������ȫ���ĳ���
	 */
	public static String RFLAG_ALL_VALUE = "1";
	/**
	 * ɸѡ��ѯ������ʼ���ĳ���
	 */
	public static String RFLAG_START_VALUE = "3";
	/**
	 * ɸѡ��ѯ�������յ��ĳ���
	 */
	public static String RFLAG_ARRIVE_VALUE = "4";
	/**
	 * ɸѡ��ѯ������ʼ���յ��ĳ���
	 */
	public static String RFLAG_START_ARRIVE_VALUE = "5";
	/**
	 * ɸѡ��ѯ������·���ĳ���
	 */
	public static String RFLAG_PASS_VALUE = "6";
	

	public static String NAME_CKBALL = "name_ckball";// ȫ������ֵvalue_ckball�����û��ȫ��post��Ϣ����Ӵ�ѡ��
	public static String NAME_CKBALL_VALUE = "value_ckball";

	// ����ֱ�Ϊ����/ֱ��/�ؿ�/����/�տ�/�տ�/�ٿͣ���û��ѡ�����post������
	public static String TFLAGDC = "tFlagDC";
	public static String TFLAGZ = "tFlagZ";
	public static String TFLAGT = "tFlagT";
	public static String TFLAGK = "tFlagK";
	public static String TFLAGPK = "tFlagPK";
	public static String TFLAGPKE = "tFlagPKE";
	public static String TFLAGLK = "tFlagLK";

	/**
	 * ˽�е�post�����ֶ�
	 */
	private String postData;


	/**
	 * ��ȡPostDataProvider��һ��ʵ��
	 * @return
	 */
	public static PostDataProvider getInstance() {
		return new PostDataProvider();
	}

	private PostDataProvider() {
	}
	
	/**
	 * ��ȡPost������
	 * 
	 * @return post����
	 */
	public String getPostData() {
		return this.postData;
	}

	/**
	 * ����Post������
	 * 
	 * @param nmonth3 �·�
	 * @param nday3 ��
	 * @param startStation_ticketLeft ��վ
	 * @param arriveStation_ticketLeft ��վ
	 * @param trainCode ����
	 * @param rFlag ɸѡ��ȫ��1��ʼ��3���յ�4��ʼ���յ�5����·6��
	 * @return ����postData��
	 */
	public String setPostData(String nmonth3, String nday3,
			String startStation_ticketLeft, String arriveStation_ticketLeft,
			String trainCode, String rFlag) {
		postData = this.NMONTH3 + "=" + nmonth3 + "&" + this.NMONTH3_NEW_VALUE
				+ "=" + "true" + "&" + this.NDAY3 + "=" + nday3 + "&"
				+ this.NDAY3_NEW_VALUE + "=" + "false" + "&"
				+ this.STARTSTATION_TICKETLEFT + "=" + startStation_ticketLeft
				+ "&" + this.STARTSTATION_TICKETLEFT_NEW_VALUE + "=" + "true"
				+ "&" + this.ARRIVESTATION_TICKETLEFT + "="
				+ arriveStation_ticketLeft + "&"
				+ this.ARRIVESTATION_TICKETLEFT_NEW_VALUE + "=" + "true" + "&"
				+ this.TRAINCODE + "=" + trainCode + "&"
				+ this.TRAINCODE_NEW_VALUE + "=" + "true" + "&" + this.RFLAG
				+ "=" + rFlag;
		return postData;
	}

	/**
	 * �������post���ݵļ�ֵ��Ϣ
	 * 
	 * @param key
	 *            post���ݼ�
	 * @param value
	 *            post������ֵ
	 */
	public void addPostData(String key, String value) {
		if (postData != "") {
			postData = postData + "&" + key + "=" + value;
		} else {
			postData = key + "=" + value;
		}
	}

	/**
	 * Ĭ�ϲ������ã����л𳵵ĵȼ�ȫѡʱ
	 */
	public void addPostData() {
		addPostData(PostDataProvider.NAME_CKBALL,
				PostDataProvider.NAME_CKBALL_VALUE);
		addPostData(PostDataProvider.TFLAGDC, "DC");
		addPostData(PostDataProvider.TFLAGZ, "Z");
		addPostData(PostDataProvider.TFLAGT, "T");
		addPostData(PostDataProvider.TFLAGK, "K");
		addPostData(PostDataProvider.TFLAGPK, "PK");
		addPostData(PostDataProvider.TFLAGPKE, "PKE");
		addPostData(PostDataProvider.TFLAGLK, "LK");
	}

	/**
	 * ����Ķ����post����
	 * @param value
	 *            lx��ֵ����ͨ��ѯ00;ѧ�������ѯ0X��ũ�������ѯ0M
	 * @return ��ӳɹ��󷵻�true
	 */
	public boolean addLXPostData(String value) {
		boolean flag = false;
		if (this.postData != "") {
			postData = "lx" + "=" + value + "&" + postData;
			flag = true;
		}
		return flag;
	}
}
