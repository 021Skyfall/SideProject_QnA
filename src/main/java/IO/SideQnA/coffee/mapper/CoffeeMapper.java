package IO.SideQnA.coffee.mapper;

import IO.SideQnA.coffee.dto.CoffeePatchDto;
import IO.SideQnA.coffee.dto.CoffeePostDto;
import IO.SideQnA.coffee.dto.CoffeeResponseDto;
import IO.SideQnA.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
