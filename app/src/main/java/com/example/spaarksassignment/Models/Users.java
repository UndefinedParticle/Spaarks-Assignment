package com.example.spaarksassignment.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public Users(int id, String name, String username, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(username);
        parcel.writeString(email);
        parcel.writeParcelable(address, i);
    }

    public static final Parcelable.Creator<Users> CREATOR = new Parcelable.Creator<Users>() {
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

    private Users(Parcel in) {
        name = in.readString();
        username = in.readString();
        email = in.readString();
        address = in.readParcelable(Address.class.getClassLoader());
    }

    public static class Address implements Parcelable {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private Geo geo;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getSuite() {
            return suite;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public Geo getGeo() {
            return geo;
        }

        public void setGeo(Geo geo) {
            this.geo = geo;
        }

        // Implement Parcelable methods
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(street);
            parcel.writeString(suite);
            parcel.writeString(city);
            parcel.writeString(zipcode);
            parcel.writeParcelable(geo, flags);
        }

        public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
            public Address createFromParcel(Parcel in) {
                return new Address(in);
            }

            public Address[] newArray(int size) {
                return new Address[size];
            }
        };

        private Address(Parcel in) {
            street = in.readString();
            suite = in.readString();
            city = in.readString();
            zipcode = in.readString();
            geo = in.readParcelable(Geo.class.getClassLoader());
        }
    }

    public static class Geo implements Parcelable {
        private String lat;
        private String lng;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        // Implement Parcelable methods
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(lat);
            parcel.writeString(lng);
        }

        public static final Parcelable.Creator<Geo> CREATOR = new Parcelable.Creator<Geo>() {
            public Geo createFromParcel(Parcel in) {
                return new Geo(in);
            }

            public Geo[] newArray(int size) {
                return new Geo[size];
            }
        };

        private Geo(Parcel in) {
            lat = in.readString();
            lng = in.readString();
        }
    }

    public static class Company implements Parcelable {
        private String name;
        private String catchPhrase;
        private String bs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public void setCatchPhrase(String catchPhrase) {
            this.catchPhrase = catchPhrase;
        }

        public String getBs() {
            return bs;
        }

        public void setBs(String bs) {
            this.bs = bs;
        }

        // Implement Parcelable methods
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(name);
            parcel.writeString(catchPhrase);
            parcel.writeString(bs);
        }

        public static final Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {
            public Company createFromParcel(Parcel in) {
                return new Company(in);
            }

            public Company[] newArray(int size) {
                return new Company[size];
            }
        };

        private Company(Parcel in) {
            name = in.readString();
            catchPhrase = in.readString();
            bs = in.readString();
        }
    }
}
