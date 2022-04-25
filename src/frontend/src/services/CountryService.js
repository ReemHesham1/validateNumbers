import http from "../http-common";

class CountryService {
  getCustomers(params) {
    return http.get("/customers/details", { params });
  }

  getCountries() {
    return http.get("/countries/names");
  }
}

export default new CountryService();
