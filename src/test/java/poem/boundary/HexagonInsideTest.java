package poem.boundary;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import org.junit.runner.RunWith;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;

@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "poem")
public class HexagonInsideTest {
	@ArchTest
	public static final ArchRule useCaseModelIsOnlyAccessedByBoundaryClassInBoundaryPackage = classes().that()
			.haveSimpleNameEndingWith("UseCaseModel").should().onlyBeAccessed().byClassesThat()
			.haveSimpleNameEndingWith("Boundary").andShould().onlyBeAccessed().byAnyPackage("..hexagon.boundary");

	@ArchTest
	public static final ArchRule commandHandlersAreOnlyAccessedByBoundaryClassInBoundaryPackage = classes().that()
			.resideInAPackage("..hexagon.internal.commandhandler..").should().onlyBeAccessed().byClassesThat()
			.haveSimpleNameEndingWith("Boundary").andShould().onlyBeAccessed().byAnyPackage("..hexagon.boundary");

	@ArchTest
	public static final ArchRule commandHandlersOnlyAccessCommandsAndDrivenPortsAndDomainAndStandardJava = classes()
			.that().resideInAPackage("..hexagon.internal.commandhandler..").should().onlyAccessClassesThat()
			.resideInAnyPackage("..hexagon.boundary.command..", "..hexagon.boundary.drivenport..",
					"..hexagon.internal.domain..", "java.lang..", "java.util..");

	@ArchTest
	public static final ArchRule domainIsOnlyAccessedByCommandHandlers = classes().that()
			.resideInAPackage("..hexagon.internal.domain..").should().onlyBeAccessed()
			.byAnyPackage("..hexagon.internal.commandhandler..");
}
