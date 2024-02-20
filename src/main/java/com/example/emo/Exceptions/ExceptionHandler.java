package com.example.emo.Exceptions;

import com.netflix.graphql.types.errors.TypedGraphQLError;
import graphql.GraphQLError;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
public class ExceptionHandler implements DataFetcherExceptionHandler {
    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters){
        if (handlerParameters.getException() instanceof UserNotFoundException) {
            //Map<String, Object> debugInfo = new HashMap<>();
            //debugInfo.put("somefield", "somevalue");

            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message("no User exists with the given id")
                    //.debugInfo(debugInfo)
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();

            return CompletableFuture.completedFuture(result);
        }
        else if(handlerParameters.getException() instanceof UserAlreadyExistsException) {
            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message("User already exists with given id")
                    .path(handlerParameters.getPath()).build();
            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();
            return CompletableFuture.completedFuture(result);
        }
        else{
            GraphQLError graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                    .message("Unknown Error")
                    .path(handlerParameters.getPath()).build();

            DataFetcherExceptionHandlerResult result = DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build();
            return CompletableFuture.completedFuture(result);
        }
    }
}
