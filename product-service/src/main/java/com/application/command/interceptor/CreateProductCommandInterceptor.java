package com.application.command.interceptor;

import com.application.command.CreateProductCommand;
import com.application.core.data.ProductLookupEntity;
import com.application.core.repository.ProductLookupRepository;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    private final ProductLookupRepository productLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            logger.info("Dispatching command: {}", command.getPayloadType());

            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                Optional<ProductLookupEntity> productLookupEntity = productLookupRepository.findByProductIdOrTitle(
                        createProductCommand.getProductId(),
                        createProductCommand.getTitle()
                );

                if (productLookupEntity.isPresent()) {
                    throw new IllegalStateException(String.format("Product with productId: %s or title: %s already exists",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }

            return command;
        };
    }
}
