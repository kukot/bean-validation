package network.contour.app.validator

import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [LineLimitValidator::class])
annotation class LineLimit(
    val maxLine: Int = Int.MAX_VALUE,
    val maxCharPerLine: Int = Int.MAX_VALUE,
    val message: String = "{network.contour.app.validator.LineLimit.message}",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class LineLimitValidator(
    private var maxLine: Int = Int.MAX_VALUE,
    private var maxCharPerLine: Int = Int.MAX_VALUE
) : ConstraintValidator<LineLimit, String?> {
    override fun initialize(constraintAnnotation: LineLimit) {
        this.maxLine = constraintAnnotation.maxLine
        this.maxCharPerLine = constraintAnnotation.maxCharPerLine
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return true
        }
        val lines = value.lines()
        return lines.size <= maxLine && lines.none { it.length > maxCharPerLine }
    }
}