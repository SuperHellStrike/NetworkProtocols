#version 450

out vec4 FragColor;

in vec2 TexPos;

uniform sampler2D sampler;

void main() {
	vec4 color = texture(sampler, TexPos);
	if(color.a < 0.1)
		discard;
	FragColor = color;
}
