package com.zoe.GSMCellLocation;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;

/**
 * ����������ͨ���ֻ��źŻ�ȡ��վ��Ϣ
 * # ͨ��TelephonyManager ��ȡlac:mcc:mnc:cell-id
 * # MCC��Mobile Country Code���ƶ����Ҵ��루�й���Ϊ460����
 * # MNC��Mobile Network Code���ƶ�������루�й��ƶ�Ϊ0���й���ͨΪ1���й�����Ϊ2���� 
 * # LAC��Location Area Code��λ�������룻
 * # CID��Cell Identity����վ��ţ�
 * # BSSS��Base station signal strength����վ�ź�ǿ�ȡ�
 * @author android_ls
 */
public class GSMCellLocationActivity extends Activity {

    private static final String TAG = "GSMCellLocationActivity";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // ��ȡ��վ��Ϣ
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                // ����ֵMCC + MNC
                String operator = mTelephonyManager.getNetworkOperator();
                int mcc = Integer.parseInt(operator.substring(0, 3));
                int mnc = Integer.parseInt(operator.substring(3));

                // �й��ƶ����й���ͨ��ȡLAC��CID�ķ�ʽ
                GsmCellLocation location = (GsmCellLocation) mTelephonyManager.getCellLocation();
                int lac = location.getLac();
                int cellId = location.getCid();

                Log.i(TAG, " MCC = " + mcc + "\t MNC = " + mnc + "\t LAC = " + lac + "\t CID = " + cellId);

                // �й����Ż�ȡLAC��CID�ķ�ʽ
                /*CdmaCellLocation location1 = (CdmaCellLocation) mTelephonyManager.getCellLocation();
                lac = location1.getNetworkId();
                cellId = location1.getBaseStationId();
                cellId /= 16;*/
                
                // ��ȡ������վ��Ϣ
                List<NeighboringCellInfo> infos = mTelephonyManager.getNeighboringCellInfo();
                StringBuffer sb = new StringBuffer("���� : " + infos.size() + "\n");
                for (NeighboringCellInfo info1 : infos) { // ����������������ѭ��
                    sb.append(" LAC : " + info1.getLac()); // ȡ����ǰ������LAC
                    sb.append(" CID : " + info1.getCid()); // ȡ����ǰ������CID
                    sb.append(" BSSS : " + (-113 + 2 * info1.getRssi()) + "\n"); // ��ȡ������վ�ź�ǿ��
                }

                Log.i(TAG, " ��ȡ������վ��Ϣ:" + sb.toString());

            }
        });

    }

}