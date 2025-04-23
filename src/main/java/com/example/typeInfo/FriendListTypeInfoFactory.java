package com.example.typeInfo;

import org.apache.flink.api.common.typeinfo.TypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInfoFactory;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.example.model.User;
import org.apache.flink.api.java.typeutils.ListTypeInfo;

public class FriendListTypeInfoFactory extends TypeInfoFactory<List<User>> {

    @Override
    public TypeInformation<List<User>> createTypeInfo(Type t, Map<String, TypeInformation<?>> genericParams) {
        return new ListTypeInfo<>(TypeInformation.of(User.class));
    }
}


// package com.auvik.alerting.common.structs.generated;

// import java.util.Map;
// import java.util.List;
// import java.lang.reflect.Type;
// import org.apache.flink.api.common.typeinfo.TypeInfoFactory;
// import org.apache.flink.api.common.typeinfo.TypeInformation;
// import org.apache.flink.api.java.typeutils.ListTypeInfo;

// public class ListOfAggregatedPeerTypeInfoFactory extends TypeInfoFactory<List<AggregatedPeer>> {
//     @Override
//     public TypeInformation<List<AggregatedPeer>> createTypeInfo(Type t, Map<String, TypeInformation<?>> genericParameters) {
//         return new ListTypeInfo<>(TypeInformation.of(AggregatedPeer.class));
//     }
// }


// @Generated("jsonschema2pojo")
// public class AggregatedRouteMonitoring {

//     /**
//      * The unique identifier for the subscriber that this document pertains to
//      * 
//      */
//     @JsonProperty("tenantId")
//     @JsonPropertyDescription("The unique identifier for the subscriber that this document pertains to")
//     private String tenantId;
//     /**
//      * The unique identifier for this device
//      * 
//      */
//     @JsonProperty("deviceId")
//     @JsonPropertyDescription("The unique identifier for this device")
//     private String deviceId;
//     @JsonProperty("aggregatedPeers")
//     @TypeInfo(ListOfAggregatedPeerTypeInfoFactory.class)
//     private List<AggregatedPeer> aggregatedPeers = new ArrayList<AggregatedPeer>();

// ….