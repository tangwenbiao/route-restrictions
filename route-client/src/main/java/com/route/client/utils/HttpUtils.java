package com.route.client.utils;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 16:51
 */
public class HttpUtils {

  private static Gson gson = new Gson();

  public static <T> HttpResponse<T> get(HttpRequest request, Class<T> type) {
    Function<String, T> function = s -> gson.fromJson(s, type);
    return get(request, function);
  }

  public static <T> HttpResponse<T> get(HttpRequest request, Type type) {
    Function<String, T> function = s -> gson.fromJson(s, type);
    return get(request, function);
  }

  private static <T> HttpResponse<T> get(HttpRequest request, Function<String, T> function) {
    HttpURLConnection urlConnection;
    InputStreamReader inputStreamReader = null;
    int responseCode = 0;
    String responseBody = null;
    try {
      urlConnection = (HttpURLConnection) new URL(request.getUrl())
          .openConnection();
      urlConnection.setRequestMethod("GET");
      if (request.getConnectTimeOut() != null) {
        urlConnection.setConnectTimeout(request.getConnectTimeOut());
      }
      if (request.getReadTimeOut() != null) {
        urlConnection.setReadTimeout(request.getReadTimeOut());
      }

      urlConnection.connect();
      responseCode = urlConnection.getResponseCode();

      inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),
          StandardCharsets.UTF_8);
      responseBody = CharStreams.toString(inputStreamReader);
    } catch (IOException e) {
      throw new RuntimeException("access to the server failed! url:{}" + request.getUrl(), e);
    } finally {
      if (inputStreamReader != null) {
        try {
          inputStreamReader.close();
        } catch (IOException e) {
        }
      }
      if (responseCode == 200) {
        return new HttpResponse(responseBody, responseCode);
      }
    }
    throw new RuntimeException(
        "the response code is err! code:" + responseCode + "body:" + responseBody);
  }

}
