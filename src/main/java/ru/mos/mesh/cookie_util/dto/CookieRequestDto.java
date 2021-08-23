package ru.mos.mesh.cookie_util.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Data;

@Data
public class CookieRequestDto {
  private String iss;
  private Integer sub;
  private UUID report;
  private Integer idmu;
  private Integer vedid;
  private String secret;

  public Map<String, Object> getMap() {
    Date now = new Date();
    Date exp = Date.from(now.toInstant().plusSeconds(10000));

    Map<String, Object> res = new HashMap<>();
    res.put("iss", iss);
    res.put("sub", sub);
    res.put("exp", exp);
    res.put("report", report);
    res.put("idmu", idmu);
    res.put("vedid", vedid);

    return res;
  }
}
