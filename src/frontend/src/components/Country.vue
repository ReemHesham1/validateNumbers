<template>
  <v-row align="center" class="list px-3 mx-auto">
    <v-col cols="12" sm="12">
      <v-row class="ma-3">
        <v-col cols="4" sm="3">
          <v-select
            v-model="countryName"
            :items="countryNames"
            label="Filter by country name"
            @change="handleCountryNameChange"
            color = "green darken-1"
            item-color = "green darken-1"
          ></v-select>
        </v-col>
        <v-col cols="4" sm="3">
          <v-select
            v-model="state"
            :items="states"
            label="Filter by phone state"
            @change="handleStateChange"
            color = "green darken-1"
            item-color = "green darken-1"
          ></v-select>
        </v-col>
      </v-row>
    </v-col>

    <v-col cols="12" sm="12">
        <v-card-title>Customer Details</v-card-title>
        <v-data-table
          :headers="headers"
          :items="customers"
          disable-pagination
          :hide-default-footer="true"
        >
          <template v-slot:item.valid="{ item }">
            <v-icon small class="mr-2" v-if="item.valid" color="green"
              >mdi-check-circle</v-icon
            >
            <v-icon small v-if="!item.valid" color="red"
              >mdi-close-circle</v-icon
            >
          </template>
        </v-data-table>
    </v-col>
    <v-col cols="12" sm="12">
      <v-row class="ma-3">
        <v-col cols="4" sm="3">
          <v-select
            v-model="pageSize"
            :items="pageSizes"
            label="Items per Page"
            @change="handlePageSizeChange"
            color = "green darken-1"
            item-color = "green darken-1"
          ></v-select>
        </v-col>

        <v-col cols="12" sm="9">
          <v-pagination
            v-model="page"
            :length="totalPages"
            total-visible="7"
            next-icon="mdi-menu-right"
            prev-icon="mdi-menu-left"
            @input="handlePageChange"
            color = "green darken-1"
          ></v-pagination>
        </v-col>
      </v-row>
    </v-col>
  </v-row>
</template>

<script>
import CountryService from "../services/CountryService";
export default {
  name: "Details-list",
  data() {
    return {
      customers: [],
      headers: [
        {
          text: "Customer name",
          align: "start",
          sortable: true,
          value: "customerName",
        },
        { text: "Country code", value: "countryCode", sortable: true },
        { text: "Phone number", value: "phoneNumber", sortable: true },
        { text: "Country name", value: "countryName", sortable: true },
        { text: "State", value: "valid", sortable: true },
      ],
      page: 1,
      totalPages: 0,
      pageSize: 5,
      pageSizes: [5, 10, 15],
      countryName: "All",
      countryNames: ["All"],
      state: "All",
      states: [
        "All",
        "Valid Number",
        "Invalid Number"
      ],
    };
  },
  methods: {
    getRequestParams(page, pageSize, countryName, state) {
      let params = {};

      if (page) {
        params["page"] = page - 1;
      }

      if (pageSize) {
        params["size"] = pageSize;
      }

      if (countryName) {
        params["countryName"] = countryName;
      }

      if (state) {
        params["state"] = state;
      }

      return params;
    },
    retrieveValidatedNumbers() {
      const params = this.getRequestParams(
        this.page,
        this.pageSize,
        this.countryName,
        this.state
      );

      CountryService.getCustomers(params)
        .then((response) => {
          const { totalPages, content } = response.data;
          this.customers = content;
          this.totalPages = totalPages;
        })
        .catch((e) => {
          console.log(e);
        });
    },

    handlePageChange() {
      this.retrieveValidatedNumbers();
    },

    handlePageSizeChange() {
      this.page = 1;
      this.retrieveValidatedNumbers();
    },

    handleCountryNameChange() {
      this.page = 1;
      this.retrieveValidatedNumbers();
    },

    handleStateChange() {
      this.page = 1;
      this.retrieveValidatedNumbers();
    },

    loadCountries() {
      CountryService.getCountries()
        .then((response) => {
          this.countryNames.push.apply(this.countryNames, response.data);
        })
        .catch((e) => {
          console.log(e);
        });
      
    },
  },
  mounted() {
    this.loadCountries();
    this.retrieveValidatedNumbers();
  },
};
</script>

<style>
.list {
  max-width: 750px;
}
</style>
