package com.zoe.slidingmenu.view;

/**
 * ��ǰ�����״̬
 * @author carlos carlosk@163.com
 * @version ����ʱ�䣺2013-4-16 ����10:52:25 ��˵��
 */

public enum SlidingState {
	SHOWLEFT("��ʾ���"),//��ʾ��� 
	SHOWCENTER("��ʾ�м�"),//��ʾ�м�
	SHOWRIGHT("��ʾ�ұ�");//��ʾ�ұ�
	
	
	private final String desc;
	private SlidingState(String desc) {
		this.desc = desc;
	}
	/**
	 * ��ȡע��
	 * @author carlos carlosk@163.com
	 * @version ����ʱ�䣺2013-4-16 ����11:10:38
	 * @return
	 */
	public String getDesc(){
		return desc;
	}
}
