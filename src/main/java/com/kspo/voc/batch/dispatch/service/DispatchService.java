package com.kspo.voc.batch.dispatch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kspo.voc.batch.common.util.Utilities;
import com.kspo.voc.batch.dispatch.model.DispatchEmailVo;
import com.kspo.voc.batch.dispatch.model.DispatchMessengerVo;
import com.kspo.voc.batch.dispatch.model.DispatchResult;
import com.kspo.voc.batch.dispatch.model.DispatchSmsVo;
import com.kspo.voc.batch.exception.BatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DispatchService {

	@Value("${dispatch.messenger.encoding}")
	String messengerEncoding;
	@Value("${dispatch.messenger.url}")
	String messengerUrl;
	@Value("${dispatch.messenger.method}")
	String messengerMethod;

	@Value("${dispatch.sms.encoding}")
	String smsEncoding;
	@Value("${dispatch.sms.url}")
	String smsUrl;
	@Value("${dispatch.sms.method}")
	String smsMethod;

	@Value("${dispatch.email.url}")
	String emailUrl;
	@Value("${dispatch.email.method}")
	String emailMethod;
	@Value("${dispatch.email.encoding}")
	String emailEncoding;
	@Value("${dispatch.email.sender}")
	String emailSender;
	@Value("${dispatch.email.sender-name}")
	String emailSenderName;

	public DispatchResult sendMessenger(DispatchMessengerVo vo) throws BatchException {
		return wget(messengerUrl, vo.toWebParameter(), messengerMethod, messengerEncoding);
	}

	public DispatchResult sendSms(DispatchSmsVo vo) throws BatchException {
		return wget(messengerUrl, vo.toWebParameter(), smsMethod, smsEncoding);
	}

	public DispatchResult sendEmail(DispatchEmailVo vo) throws BatchException {
		return wget(messengerUrl, vo.toWebParameter(), emailMethod, emailEncoding);
	}

	public static DispatchResult wget(String strUri, String strPost, String strMethod, String encoding) {
		BufferedReader br = null;
		OutputStreamWriter osw = null;
		String method = strMethod;
		DispatchResult result = new DispatchResult();

		try {
			URL url = new URL(strUri);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			if (conn == null)
				return result;
			conn.setDoOutput(true);
			conn.setConnectTimeout(2000);
			conn.setUseCaches(false);
			if (strPost != null) {
				if (Utilities.isEmpty(method))
					method = "POST";

				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
			} else {
				if (Utilities.isEmpty(method))
					method = "GET";
				conn.setRequestProperty("Content-Type", "text/plain");
			}
			conn.setRequestMethod(method);

			if (strPost != null && strPost.length() > 0) {
				osw = new OutputStreamWriter(conn.getOutputStream(), encoding);
				osw.write(strPost);
				osw.flush();
			}

			result.setResponseCode(conn.getResponseCode());
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));

			char[] buffer = new char[1024];
			StringBuffer sb = new StringBuffer();
			do {
				int nRead = br.read(buffer);
				if (nRead <= 0)
					break;

				sb.append(buffer, 0, nRead);
			} while (true);
			result.setResult(sb.toString());
		} catch (Exception ex) {
			log.debug(ex.toString());
			result.setErrorCode(ex.getMessage());

		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
				log.debug(ex.toString());
			}
			try {
				if (osw != null)
					osw.close();
			} catch (Exception ex) {
				log.debug(ex.toString());
			}
		}
		return result;
	}

}
