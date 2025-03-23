package com.wut.ersms.paymentservice.configuration;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import com.wut.ersms.paymentservice.auth.TPayAuthResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TPayAuthResponseSerializer implements StreamSerializer<TPayAuthResponse> {
    @Override
    public void write(ObjectDataOutput out, TPayAuthResponse object) throws IOException {
        out.writeLong(object.getIssuedAt().toEpochSecond(ZoneOffset.UTC));
        out.writeString(object.getScope());
        out.writeString(object.getTokenType());
        out.writeLong(object.getExpiresIn());
        out.writeString(object.getClientId());
        out.writeString(object.getAccessToken());
    }

    @Override
    public TPayAuthResponse read(ObjectDataInput in) throws IOException {
        return TPayAuthResponse.builder()
                .issuedAt(LocalDateTime.ofInstant(Instant.ofEpochSecond(in.readLong()), ZoneOffset.UTC))
                .scope(in.readString())
                .tokenType(in.readString())
                .expiresIn(in.readLong())
                .clientId(in.readString())
                .accessToken(in.readString())
                .build();
    }

    @Override
    public int getTypeId() {
        return 1;
    }
}
