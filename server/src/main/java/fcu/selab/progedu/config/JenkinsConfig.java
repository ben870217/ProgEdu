package fcu.selab.progedu.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import fcu.selab.progedu.exception.LoadConfigFailureException;

public class JenkinsConfig {
  private static final String PROPERTY_FILE = "/config/jenkins_config.properties";
  private static final String EXCEPTION = "Unable to get config of JENKINS"
      + " connection string from file;";
  private static JenkinsConfig instance;

  /**
   *
   * @return JenkinsConfig
   */
  public static JenkinsConfig getInstance() {
    if (instance == null) {
      instance = new JenkinsConfig();
    }
    return instance;
  }

  private Properties props;

  private JenkinsConfig() {
    InputStream is = this.getClass().getResourceAsStream(PROPERTY_FILE);
    try {
      props = new Properties();
      props.load(is);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get jenkins host url
   *
   * @return url
   * @throws LoadConfigFailureException on properties call error
   */
  public String getJenkinsHostUrl() throws LoadConfigFailureException {
    String jenkinsUrl = System.getenv("WEB_JENKINS_URL");
    System.out.println("WEB_JENKINS_URL: " + jenkinsUrl);
    if (jenkinsUrl != null && !jenkinsUrl.equals("")) {
      return jenkinsUrl;
    }
    if (props != null) {
      return props.getProperty("JENKINS_HOST_URL").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get Selenium host url
   *
   * @return url
   * @throws LoadConfigFailureException on properties call error
   */
  public String getSeleniumHostUrl() throws LoadConfigFailureException {
    String seleniumUrl = System.getenv("WEB_SELENIUM_URL");
    System.out.println("WEB_SELENIUM_URL: " + seleniumUrl);
    if (seleniumUrl != null && !seleniumUrl.equals("")) {
      return seleniumUrl;
    }
    if (props != null) {
      return props.getProperty("SELENIUM_HOST_URL").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get jenkins root username
   *
   * @return username
   * @throws LoadConfigFailureException on properties call error
   */
  public String getJenkinsRootUsername() throws LoadConfigFailureException {
    String jenkinsAdminUsername = System.getenv("WEB_JENKINS_ADMIN_USERNAME");
    System.out.println("WEB_JENKINS_ADMIN_USERNAME: " + jenkinsAdminUsername);
    if (jenkinsAdminUsername != null && !jenkinsAdminUsername.equals("")) {
      return jenkinsAdminUsername;
    }
    if (props != null) {
      return props.getProperty("JENKINS_ROOT_USERNAME").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get jenkins admin username
   *
   * @return username
   */
  public String getJenkinsAdminUsername() throws LoadConfigFailureException {
    return getJenkinsRootUsername();
  }

  /**
   * Get jenkins root password
   *
   * @return password
   * @throws LoadConfigFailureException on properties call error
   */
  public String getJenkinsRootPassword() throws LoadConfigFailureException {
    String jenkinsAdminPassword = System.getenv("WEB_JENKINS_ADMIN_PASSWORD");
    System.out.println("WEB_JENKINS_ADMIN_PASSWORD: " + jenkinsAdminPassword);
    if (jenkinsAdminPassword != null && !jenkinsAdminPassword.equals("")) {
      return jenkinsAdminPassword;
    }
    if (props != null) {
      return props.getProperty("JENKINS_ROOT_PASSWORD").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get jenkins admin password
   *
   * @return password
   */
  public String getJenkinsAdminPassword() throws LoadConfigFailureException {
    return getJenkinsRootPassword();
  }

  /**
   * Get jenkins api token
   *
   * @return token
   * @throws LoadConfigFailureException on properties call error
   */
  public String getJenkinsApiToken() throws LoadConfigFailureException {
    String jenkinsApiToken = System.getenv("WEB_JENKINS_API_TOKEN");
    System.out.println("WEB_JENKINS_API_TOKEN: " + jenkinsApiToken);
    if (jenkinsApiToken != null && !jenkinsApiToken.equals("")) {
      return jenkinsApiToken;
    }
    if (props != null) {
      return props.getProperty("JENKINS_API_TOKEN").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get jenkins root url
   *
   * @return url
   * @throws LoadConfigFailureException on properties call error
   */
  public String getJenkinsRootUrl() throws LoadConfigFailureException {
    String jenkinsHost = getJenkinsHostUrl();
    if (jenkinsHost != null && !jenkinsHost.equals("")) {
      System.out.println("JENKINS_LOGIN_URL: "
          + jenkinsHost.substring(0, jenkinsHost.indexOf("//") + 2)
          + getJenkinsAdminUsername() + ":" + getJenkinsApiToken() + "@"
          + jenkinsHost.substring(jenkinsHost.indexOf("//") + 2, jenkinsHost.length()));
      return jenkinsHost.substring(0, jenkinsHost.indexOf("//") + 2)
          + getJenkinsAdminUsername() + ":" + getJenkinsApiToken() + "@"
          + jenkinsHost.substring(jenkinsHost.indexOf("//") + 2, jenkinsHost.length());
    }
    if (props != null) {
      return props.getProperty("JENKINS_ROOT_URL").trim();
    }
    throw new LoadConfigFailureException(EXCEPTION + PROPERTY_FILE);
  }

  /**
   * Get mail username
   *
   * @return url
   * @throws LoadConfigFailureException on properties call error
   */
  public String getMailUser() throws LoadConfigFailureException {
    String mailUser = System.getenv("MAIL_USER");
    System.out.println("MAIL_USER: " + mailUser);
    if (mailUser != null && !mailUser.equals("")) {
      return mailUser;
    }
    if (props != null) {
      return props.getProperty("MAIL_USERNAME").trim();
    }
    throw new LoadConfigFailureException(
        "Unable to get config of JENKINS connection string from file;" + PROPERTY_FILE);
  }

  /**
   * Get mail password
   *
   * @return url
   * @throws LoadConfigFailureException on properties call error
   */
  public String getMailPassword() throws LoadConfigFailureException {
    String mailPassword = System.getenv("MAIL_PASSWORD");
    System.out.println("MAIL_PASSWORD: " + mailPassword);
    if (mailPassword != null && !mailPassword.equals("")) {
      return mailPassword;
    }
    if (props != null) {
      return props.getProperty("MAIL_PASSWORD").trim();
    }
    throw new LoadConfigFailureException(
        "Unable to get config of JENKINS connection string from file;" + PROPERTY_FILE);
  }

}
